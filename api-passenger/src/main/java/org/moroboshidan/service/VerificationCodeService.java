package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.IdentityConstant;
import org.moroboshidan.internalcommon.constant.TokenConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.internalcommon.response.NumberCodeResponse;
import org.moroboshidan.internalcommon.response.TokenResponse;
import org.moroboshidan.internalcommon.util.JwtUtils;
import org.moroboshidan.internalcommon.util.RedisUtils;
import org.moroboshidan.remote.ServicePassengerUserClient;
import org.moroboshidan.remote.ServiceVerificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationClient serviceVerificationClient;
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult generateCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        log.info("调用验证码服务，获取验证码");
        NumberCodeResponse numberCodeResponse = serviceVerificationClient.getNumberCode(6).getData();
        int numberCode = numberCodeResponse.getNumberCode();
        log.info("generated number code: " +numberCode);
        // 存入redis
        stringRedisTemplate.opsForValue().set(RedisUtils.generateKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY), numberCode + "", 2, TimeUnit.MINUTES);
        log.info("将验证码存入redis");
        // todo 通过短信服务，将验证码发送至对应的手机上

        // 返回结果
        return ResponseResult.success();
    }

    /**
     * 用户输入收到的验证码，后端校验
     * @param
     * @return
     * @throws
     *
     */
    public ResponseResult checkVerificationCode(String passengerPhone, String verificationCode) {
        String codeInRedis = stringRedisTemplate.opsForValue().get(RedisUtils.generateKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY));
        log.info("redis key is: " + RedisUtils.generateKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY));
        log.info("verification code in redis: " + codeInRedis);
        if (StringUtils.isBlank(codeInRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!codeInRedis.equals(verificationCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 判断是否有用户，并进行对应的操作
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegistry(verificationCodeDTO);
        // 颁发令牌
        String accessToken = JwtUtils.generateToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        // 将token存入redis
        String accessTokenKey = RedisUtils.generateTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshTokenKey = RedisUtils.generateTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS); // refresh token过期时间需要久一点
        // 响应
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
