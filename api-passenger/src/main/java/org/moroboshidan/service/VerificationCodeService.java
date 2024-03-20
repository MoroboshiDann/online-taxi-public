package org.moroboshidan.service;

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
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationClient serviceVerificationClient;
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult generateCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code: " +numberCode);
        // 存入redis
        // 需要 key, value
        String key = RedisUtils.generateKey(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);
        System.out.println("将验证码存入redis");
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
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        String storedCode = stringRedisTemplate.opsForValue().get(RedisUtils.generateKey(passengerPhone));
        System.out.println("redis key is: " + RedisUtils.generateKey(passengerPhone));
        System.out.println("verification code in redis: " + storedCode);
        if (StringUtils.isBlank(storedCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!storedCode.equals(verificationCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 判断是否有用户，并进行对应的操作
        // VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        // verificationCodeDTO.setPassengerPhone(passengerPhone);
        // servicePassengerUserClient.loginOrRegistry(verificationCodeDTO);
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
