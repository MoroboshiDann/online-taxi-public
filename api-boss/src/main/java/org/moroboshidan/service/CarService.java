package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.Car;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }
}
