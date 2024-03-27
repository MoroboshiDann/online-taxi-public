package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.internalcommon.response.TrackResponse;
import org.moroboshidan.mapper.CarMapper;
import org.moroboshidan.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car) {
        car.setGmtCreate(LocalDateTime.now());
        car.setGmtUpdate(LocalDateTime.now());
        carMapper.insert(car);
        // 为此车辆在猎鹰服务中创建对应的terminal，并返回其tid
        TerminalResponse terminalResponse = serviceMapClient.addTerminal(car.getVehicleNo(), car.getId().toString() ).getData();
        String tid = terminalResponse.getTid();
        car.setTid(tid);
        // 为此车辆的terminal创建一条轨迹，并返回其trid
        TrackResponse trackResponse = serviceMapClient.addTrack(tid).getData();
        String trid = trackResponse.getTrid();
        String trname = trackResponse.getTrname();
        car.setTrid(trid);
        car.setTrname(trname);
        carMapper.updateById(car);
        return ResponseResult.success();
    }

    public ResponseResult<Car> getById(Long carId) {
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Car::getId, carId);
        List<Car> cars = carMapper.selectList(queryWrapper);
        if (cars.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.CAR_NOT_EXISTS.getCode(), CommonStatusEnum.CAR_NOT_EXISTS.getValue());
        }
        return ResponseResult.success(cars.get(0));
    }
}
