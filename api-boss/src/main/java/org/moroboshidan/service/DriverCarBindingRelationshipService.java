package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.DriverCarBindingRelationship;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;


    public ResponseResult bindDriverCar(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.bindDriverCarRelationship(driverCarBindingRelationship);
    }

    public ResponseResult unbindDriverCar(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.unbindDriverCarRelationship(driverCarBindingRelationship);
    }
}
