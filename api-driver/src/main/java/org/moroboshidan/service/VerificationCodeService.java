package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    public ResponseResult checkAndSendVerificationCode(VerificationCodeDTO verificationCodeDTO) {
        // 查询service-driver-user，改手机号对应的用户是否存在

        // 调用service-verificationcode获取验证码
        return null;
    }
}
