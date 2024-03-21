package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.ForecastPriceResponse;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceService {

    /**
     * 根据出发地和目的地的经纬度计算预估价格
     * @param
     * @return
     * @throws
     *
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(3.14);
        return ResponseResult.success(forecastPriceResponse);
    }
}
