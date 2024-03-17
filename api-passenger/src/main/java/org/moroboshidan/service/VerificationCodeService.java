package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.NumberCodeResponse;
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
        String key = verificationCodePrefix + passengerPhone;
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);
        System.out.println("将验证码存入redis");
        // 通过短信服务，将验证码发送至对应的手机上

        // 返回结果
        return ResponseResult.success();
    }

    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        String storedCode = stringRedisTemplate.opsForValue().get(verificationCodePrefix + passengerPhone);
        if (verificationCode == null || !verificationCode.equals(storedCode)) {
            return ResponseResult.fail(0, "incorrect code", "");
        }
        return ResponseResult.success("");
    }
}
