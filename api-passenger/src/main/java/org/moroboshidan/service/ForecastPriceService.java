package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ForecastPriceDTO;
import org.moroboshidan.internalcommon.response.ForecastPriceResponse;
import org.moroboshidan.remote.ServicePriceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastPriceService {
    @Autowired
    private ServicePriceClient servicePriceClient;

    /**
     * 根据出发地和目的地的经纬度计算预估价格
     *
     * @param
     * @return
     * @throws
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        ResponseResult responseResult = servicePriceClient.forecastPrice(new ForecastPriceDTO(depLongitude, depLatitude, destLongitude, destLatitude));
        return ResponseResult.success(responseResult.getData());
    }
}
