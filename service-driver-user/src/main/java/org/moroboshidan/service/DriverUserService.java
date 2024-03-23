package org.moroboshidan.service;

import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.DriverCarConstants;
import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.DriverUserExistsResponse;
import org.moroboshidan.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult tetGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }

    public ResponseResult addDriverUser(DriverUser driverUser) {
        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtUpdate(LocalDateTime.now());
        driverUserMapper.insert(driverUser);
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
        DriverUserExistsResponse driverUserExistsResponse = new DriverUserExistsResponse();;
        if (driverUsers.isEmpty()) {
            driverUserExistsResponse.setDriverPhone(null);
            driverUserExistsResponse.setIfExists(DriverCarConstants.DRIVER_NOT_EXISTS);
        } else {
            driverUserExistsResponse.setDriverPhone(driverUsers.get(0).getDriverPhone());
            driverUserExistsResponse.setIfExists(DriverCarConstants.DRIVER_EXISTS);
        }
        return ResponseResult.success(driverUserExistsResponse);
    }
}
