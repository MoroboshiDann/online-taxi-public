package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ForecastPriceDTO;
import org.moroboshidan.internalcommon.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.GET, value = "/direction/driving")
    ResponseResult<DirectionResponse> driving(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
