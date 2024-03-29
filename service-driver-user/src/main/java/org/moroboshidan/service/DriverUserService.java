package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.DriverCarConstants;
import org.moroboshidan.internalcommon.dto.DriverCarBindingRelationship;
import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.DriverUserWorkStatus;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.DriverUserExistsResponse;
import org.moroboshidan.internalcommon.response.OrderDriverResponse;
import org.moroboshidan.mapper.DriverCarBindingRelationshipMapper;
import org.moroboshidan.mapper.DriverUserMapper;
import org.moroboshidan.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult tetGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }

    public ResponseResult addDriverUser(DriverUser driverUser) {
        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtUpdate(LocalDateTime.now());
        driverUserMapper.insert(driverUser);
        // 初始化司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_OFF_WORK);
        driverUserWorkStatus.setGmtCreate(LocalDateTime.now());
        driverUserWorkStatus.setGmtModified(LocalDateTime.now());
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);
        return ResponseResult.success();
    }

    public ResponseResult updateDriverUser(DriverUser driverUser) {
        driverUser.setGmtUpdate(LocalDateTime.now());
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success();
    }

    public ResponseResult<DriverUserExistsResponse> getDriverUserByPhone(String driverPhone) {
        Map<String, Object> map = new HashMap<>();
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();
        ;
        if (driverUsers.isEmpty()) {
            driverUserExistsResponse.setDriverPhone(null);
            driverUserExistsResponse.setIfExists(DriverCarConstants.DRIVER_NOT_EXISTS);
        } else {
            driverUserExistsResponse.setDriverPhone(driverUsers.get(0).getDriverPhone());
            driverUserExistsResponse.setIfExists(DriverCarConstants.DRIVER_EXISTS);
        }
        return ResponseResult.success(driverUserExistsResponse);
    }

    public ResponseResult<Boolean> hasAvailableDriver(String cityCode) {
        boolean flag = driverUserMapper.selectDriverCountByCityCode(cityCode) > 0;
        return ResponseResult.success(flag);
    }

    /**
     * @description: 根据车辆id查找可用司机
     * @param carId
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult<org.moroboshidan.internalcommon.response.OrderDriverResponse>
     * @author: MoroboshiDan
     * @time: 2024/3/27 16:25
     */
    public ResponseResult<OrderDriverResponse> getAvailableDriver(Long carId) {

        LambdaQueryWrapper<DriverCarBindingRelationship> bindingRelationshipWrapper = new LambdaQueryWrapper<>();
        bindingRelationshipWrapper.eq(DriverCarBindingRelationship::getCarId, carId);
        bindingRelationshipWrapper.eq(DriverCarBindingRelationship::getBindState, DriverCarConstants.DRIVER_CAR_BIND);
        DriverCarBindingRelationship relationship = driverCarBindingRelationshipMapper.selectOne(bindingRelationshipWrapper);
        if (relationship == null) {
            return ResponseResult.fail(CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode(), CommonStatusEnum.NO_AVAILABLE_DRIVER.getValue());
        }
        LambdaQueryWrapper<DriverUserWorkStatus> workStatusWrapper = new LambdaQueryWrapper<>();
        workStatusWrapper.eq(DriverUserWorkStatus::getDriverId, relationship.getDriverId());
        workStatusWrapper.eq(DriverUserWorkStatus::getWorkStatus, DriverCarConstants.DRIVER_WORK_STATUS_ONGOING);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(workStatusWrapper);
        if (driverUserWorkStatus == null) {
            return ResponseResult.fail(CommonStatusEnum.NO_AVAILABLE_DRIVER.getCode(), CommonStatusEnum.NO_AVAILABLE_DRIVER.getValue());
        }
        LambdaQueryWrapper<DriverUser> driverUserWrapper = new LambdaQueryWrapper<>();
        driverUserWrapper.eq(DriverUser::getId, driverUserWorkStatus.getDriverId());
        DriverUser driverUser = driverUserMapper.selectOne(driverUserWrapper);
        return ResponseResult.success(new OrderDriverResponse(driverUser.getId(), driverUser.getDriverPhone(), carId));
    }
}
