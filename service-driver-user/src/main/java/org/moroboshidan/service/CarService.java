package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.mapper.CarMapper;
import org.moroboshidan.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ServiceMapClient serviceMapClient;
    public ResponseResult addCar(Car car) {
        car.setGmtCreate(LocalDateTime.now());
        car.setGmtUpdate(LocalDateTime.now());
        // 获取车辆对应的tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = responseResult.getData().getTid();
        car.setTid(tid);
        carMapper.insert(car);
        return ResponseResult.success();
    }
}
