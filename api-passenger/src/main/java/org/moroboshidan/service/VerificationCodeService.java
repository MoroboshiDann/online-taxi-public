package org.moroboshidan.service;

import org.apache.commons.lang.StringUtils;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.IdentityConstant;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.internalcommon.response.NumberCodeResponse;
import org.moroboshidan.internalcommon.response.TokenResponse;
import org.moroboshidan.internalcommon.util.JwtUtils;
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

    private String verificationCodePrefix = "passenger-verification";

    public ResponseResult generateCode(String passengerPhone) {
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code: " +numberCode);
        // 存入redis
        // 需要 key, value
        String key = generateKey(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);
        System.out.println("将验证码存入redis");
        // 通过短信服务，将验证码发送至对应的手机上

        // 返回结果
        return ResponseResult.success();
    }

    /**
     * 生成redis key
     * @param
     * @return
     * @throws
     *
     */
    private String generateKey(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 用户输入收到的验证码，后端校验
     * @param
     * @return
     * @throws
     *
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        String storedCode = stringRedisTemplate.opsForValue().get(generateKey(passengerPhone));
        System.out.println("verification code in redis: " + storedCode);
        if (StringUtils.isBlank(storedCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!storedCode.equals(verificationCode)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 判断是否有用户，并进行对应的操作
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegistry(verificationCodeDTO);
        // 颁发令牌
        String token = JwtUtils.generateToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        // 响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}
