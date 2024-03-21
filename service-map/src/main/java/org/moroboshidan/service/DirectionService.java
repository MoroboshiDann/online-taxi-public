package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.DirectionResponse;
import org.moroboshidan.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectionService {
    @Autowired
    private MapDirectionClient mapDirectionClient;
    /**
     * 根据起点和重点经纬度，获取距离和时长
     * @param
     * @return
     * @throws
     *
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return ResponseResult.success(direction);
    }
}
