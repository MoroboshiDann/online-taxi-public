package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.DriverUserWorkStatus;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverUserWorkStatusService {
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("driver_id", driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(queryMap);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatus.setGmtModified(LocalDateTime.now());
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);
        return ResponseResult.success();
    }
}
