package org.moroboshidan.service;

import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ApiDriverRequest;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult upload(ApiDriverRequest apiDriverRequest) {
        // 通过car id 获取tid 和 trid
        Long carId = apiDriverRequest.getCarId();
        ResponseResult<Car> result = serviceDriverUserClient.getByCarId(carId);
        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return result;
        }
        String tid = result.getData().getTid();
        String trid = result.getData().getTrid();
        return null;
    }
}
