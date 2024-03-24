package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.internalcommon.response.TrackResponse;
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
        // 为此车辆在猎鹰服务中创建对应的terminal，并返回其tid
        TerminalResponse terminalResponse = serviceMapClient.addTerminal(car.getVehicleNo()).getData();
        String tid = terminalResponse.getTid();
        car.setTid(tid);
        // 为此车辆的terminal创建一条轨迹，并返回其trid
        TrackResponse trackResponse = serviceMapClient.addTrack(tid).getData();
        String trid = trackResponse.getTrid();
        String trname = trackResponse.getTrname();
        car.setTrid(trid);
        car.setTrid(trname);
        carMapper.insert(car);
        return ResponseResult.success();
    }
}
