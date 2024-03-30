package org.moroboshidan.service;

import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.constant.IdentityConstant;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.ServiceSsePushClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
    @Autowired
    private ServiceSsePushClient serviceSsePushClient;

    public ResponseResult pushPayInfo(String orderId, String price, Long passengerId) {
        JSONObject message = new JSONObject();
        message.put("orderId", orderId);
        message.put("price", price);
        serviceSsePushClient.push(passengerId, IdentityConstant.PASSENGER_IDENTITY, message.toString());
        return ResponseResult.success();
    }
}
