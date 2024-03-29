package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<DriverUserWorkStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DriverUserWorkStatus::getDriverId, driverId);
        DriverUserWorkStatus driverUserWorkStatusRecord = driverUserWorkStatusMapper.selectOne(queryWrapper);
        driverUserWorkStatusRecord.setWorkStatus(workStatus);
        driverUserWorkStatusRecord.setGmtModified(LocalDateTime.now());
        driverUserWorkStatusMapper.updateById(driverUserWorkStatusRecord);
        return ResponseResult.success();
    }
}
