package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult tetGetDriverUser() {
        DriverUser driverUser = driverUserMapper.selectById(1);
        return ResponseResult.success(driverUser);
    }
}
