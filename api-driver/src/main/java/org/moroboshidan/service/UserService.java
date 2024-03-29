package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.DriverUser;
import org.moroboshidan.internalcommon.dto.DriverUserWorkStatus;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult updateUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateUser(driverUser);
    }

    public ResponseResult changWorkStatusOnGoing(DriverUserWorkStatus driverUserWorkStatus) {
        return serviceDriverUserClient.changeWorkStatus(driverUserWorkStatus);
    }

    public ResponseResult changWorkStatusOffWork(DriverUserWorkStatus driverUserWorkStatus) {
        return serviceDriverUserClient.changeWorkStatus(driverUserWorkStatus);
    }
}
