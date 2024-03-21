package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.ForecastPriceResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceService {
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("调用地图服务，查询距离和时长...");
        log.info("读取计价规则");
        log.info("根据距离、时长、计价规则计算预估价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(3.14);
        return  ResponseResult.success(forecastPriceResponse);
    }
}
