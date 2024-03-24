package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFromMapService {
    @Autowired
    private ServiceClient serviceClient;

    /**
     * 创建service
     * @param name
     * @return
     */
    public ResponseResult add(String name) {
       return serviceClient.add(name);
    }
}
