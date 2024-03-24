package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ApiDriverRequest;
import org.moroboshidan.internalcommon.request.PointDTO;
import org.moroboshidan.internalcommon.request.PointRequest;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.moroboshidan.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceMapClient serviceMapClient;
    public ResponseResult upload(ApiDriverRequest apiDriverRequest) {
        // 通过car id 获取tid 和 trid
        Long carId = apiDriverRequest.getCarId();
        log.info("调用service-driver-user，获取当前car id对应的车辆信息");
        Car car = serviceDriverUserClient.getByCarId(carId).getData();
        if (car == null) {
            return ResponseResult.fail(CommonStatusEnum.CAR_NOT_EXISTS.getCode(), CommonStatusEnum.CAR_NOT_EXISTS.getValue());
        }
        log.info("当前车辆信息：" + car.toString());
        log.info("调用service-map将轨迹点上传");
        log.info("当前轨迹信息为：" + apiDriverRequest.getPoints().toString());
        return serviceMapClient.uploadTrackPoint(new PointRequest(car.getTid(), car.getTrid(), apiDriverRequest.getPoints()));
    }
}
