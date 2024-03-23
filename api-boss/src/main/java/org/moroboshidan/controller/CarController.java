package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }
}
