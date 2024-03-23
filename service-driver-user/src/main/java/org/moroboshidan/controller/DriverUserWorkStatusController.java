package org.moroboshidan.controller;


import org.moroboshidan.internalcommon.dto.DriverUserWorkStatus;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
public class DriverUserWorkStatusController {
    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;
    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus) {
        return driverUserWorkStatusService.changeWorkStatus(driverUserWorkStatus.getDriverId(), driverUserWorkStatus.getWorkStatus());
    }
}
