package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.DriverCarConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.internalcommon.response.DriverUserExistsResponse;
import org.moroboshidan.internalcommon.response.NumberCodeResponse;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.moroboshidan.remote.ServiceVerificationcodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    public ResponseResult checkAndSendVerificationCode(VerificationCodeDTO verificationCodeDTO) {
        // 查询service-driver-user，改手机号对应的用户是否存在
        ResponseResult<DriverUserExistsResponse> responseResult = serviceDriverUserClient.checkUser(verificationCodeDTO.getDriverPhone());
        if (responseResult.getData().getIfExists() == DriverCarConstants.DRIVER_NOT_EXISTS) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info("driver exists");
        // 如果司机存在，继续流程
        // 调用service-verificationcode获取验证码
        NumberCodeResponse response = serviceVerificationcodeClient.getNumberCode(6).getData();
        int numberCode = response.getNumberCode();
        log.info("number code: " + numberCode);
        return null;
    }
}
