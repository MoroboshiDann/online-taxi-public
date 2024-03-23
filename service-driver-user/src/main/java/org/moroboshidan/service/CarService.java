package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;
    public ResponseResult addCar(Car car) {
        car.setGmtCreate(LocalDateTime.now());
        car.setGmtUpdate(LocalDateTime.now());
        carMapper.insert(car);
        return ResponseResult.success();
    }
}
