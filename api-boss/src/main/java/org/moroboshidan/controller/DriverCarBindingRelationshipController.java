package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.DriverCarBindingRelationship;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {
    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    @PostMapping("/bind")
    public ResponseResult bindDriverCar(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        return driverCarBindingRelationshipService.bindDriverCar(driverCarBindingRelationship);
    }

    @PostMapping("/unbind")
    public ResponseResult unbindDriverCar(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        return driverCarBindingRelationshipService.unbindDriverCar(driverCarBindingRelationship);
    }
}
