package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.DriverCarConstants;
import org.moroboshidan.internalcommon.constant.IdentityConstant;
import org.moroboshidan.internalcommon.constant.TokenConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.internalcommon.response.DriverUserExistsResponse;
import org.moroboshidan.internalcommon.response.NumberCodeResponse;
import org.moroboshidan.internalcommon.response.TokenResponse;
import org.moroboshidan.internalcommon.util.JwtUtils;
import org.moroboshidan.internalcommon.util.RedisUtils;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.moroboshidan.remote.ServiceVerificationcodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(VerificationCodeDTO verificationCodeDTO) {
        // 查询service-driver-user，改手机号对应的用户是否存在
        String driverPhone = verificationCodeDTO.getDriverPhone();
        ResponseResult<DriverUserExistsResponse> checkResult = serviceDriverUserClient.checkUser(driverPhone);
        if (checkResult.getData().getIfExists() == DriverCarConstants.DRIVER_NOT_EXISTS) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info("driver exists");
        // 如果司机存在，继续流程
        // 调用service-verificationcode获取验证码
        NumberCodeResponse numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6).getData();
        int numberCode = numberCodeResponse.getNumberCode();
        log.info("number code: " + numberCode);
        // todo 调用第三方服务发送验证码

        // 验证码存入redis
        stringRedisTemplate.opsForValue().set(RedisUtils.generateKey(driverPhone, IdentityConstant.DRIVER_IDENTITY), numberCode + "", 2, TimeUnit.MINUTES);

        return ResponseResult.success();
    }

    public ResponseResult checkVerificationCode(String driverPhone, String verificationCode) {
        String codeInRedis = stringRedisTemplate.opsForValue().get(RedisUtils.generateKey(driverPhone, IdentityConstant.DRIVER_IDENTITY));
        log.info("redis key is: " + RedisUtils.generateKey(driverPhone, IdentityConstant.DRIVER_IDENTITY));
        log.info("verification code in redis: " + codeInRedis);
        if (StringUtils.isBlank(codeInRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!codeInRedis.equals(verificationCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 颁发令牌
        String accessToken = JwtUtils.generateToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        // 将token存入redis
        String accessTokenKey = RedisUtils.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshTokenKey = RedisUtils.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS); // refresh token过期时间需要久一点
        // 响应
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
