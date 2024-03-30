package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ForecastPriceDTO;
import org.moroboshidan.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {
        return priceService.forecastPrice(forecastPriceDTO);
    }

    @GetMapping("/calculate-price")
    public ResponseResult calculatePrice(@RequestParam Long distance, @RequestParam Long duration, @RequestParam String cityCode, @RequestParam String vehicleType) {
        return priceService.calculatePrice(distance, duration, cityCode, vehicleType);
    }
}
