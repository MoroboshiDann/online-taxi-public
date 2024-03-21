package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.DirectionResponse;
import org.springframework.stereotype.Service;

@Service
public class DirectionService {
    /**
     * 根据起点和重点经纬度，获取距离和时长
     * @param
     * @return
     * @throws
     *
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String depLongitude1, String destLatitude) {
        DirectionResponse directionResponse = new DirectionResponse(123, 111);
        return ResponseResult.success(directionResponse);
    }
}
