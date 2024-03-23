package org.moroboshidan.controller;


import org.moroboshidan.internalcommon.dto.DriverCarBindingRelationship;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MoroboshiDan
 * @since 2024-03-23
 */
@RestController
@RequestMapping("/driver-car-binding-relationship")
public class DriverCarBindingRelationshipController {
    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    @GetMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }

    @GetMapping("/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship) {
        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
