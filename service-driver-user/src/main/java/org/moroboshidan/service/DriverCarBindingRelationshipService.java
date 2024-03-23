package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.DriverCarConstants;
import org.moroboshidan.internalcommon.dto.DriverCarBindingRelationship;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        // 先查询该司机和车辆是否已经绑定，如果是，就不允许绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        Integer count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count > 0) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getValue());
        }
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count > 0) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_ALREADY_BIND.getCode(),CommonStatusEnum.DRIVER_ALREADY_BIND.getValue());
        }
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstants.DRIVER_CAR_BIND);
        count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count > 0) {
            return ResponseResult.fail(CommonStatusEnum.CAR_ALREADY_BIND.getCode(),CommonStatusEnum.CAR_ALREADY_BIND.getValue());
        }
        driverCarBindingRelationship.setBindingTime(LocalDateTime.now());
        driverCarBindingRelationship.setBindState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success("");
    }
}
