package org.moroboshidan.service;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ForecastPriceDTO;
import org.moroboshidan.internalcommon.response.DirectionResponse;
import org.moroboshidan.internalcommon.response.ForecastPriceResponse;
import org.moroboshidan.mapper.PriceRuleMapper;
import org.moroboshidan.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ForecastPriceService {
    @Autowired
    private ServiceMapClient serviceMapClient;
    @Autowired
    private PriceRuleMapper priceRuleMapper;
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("调用地图服务，查询距离和时长...");
        ResponseResult drivingResult = serviceMapClient.driving(new ForecastPriceDTO(depLongitude, depLatitude, destLongitude, destLatitude));
        log.info(drivingResult.getData().toString());
        DirectionResponse directionResponse = (DirectionResponse) drivingResult.getData();
        log.info("读取计价规则");
        Map<String, Object> queryMap = new HashMap<>(); // 将两个主键放入map进行查询
        queryMap.put("city_code", "110000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        log.info("根据距离、时长、计价规则计算预估价格");
        getPrice(directionResponse.getDistance(), directionResponse.getDuration(), priceRules.get(0));
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(3.14);
        return  ResponseResult.success(forecastPriceResponse);
    }

    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        BigDecimal price = new BigDecimal(0);
        // 起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);
        // 里程费
        // 总里程m -> km
        BigDecimal distanceDecimal = new BigDecimal(distance);
        BigDecimal distanceKiloDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        Integer startMile = priceRule.getStartMile(); // 起步里程
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        double distanceSubtract = distanceKiloDecimal.subtract(startMileDecimal).doubleValue();
        // 计算超出起步里程的部分km
        Double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        BigDecimal mileDecimal = new BigDecimal(mile);
        // 计程单价
        BigDecimal unitPricePerMile = new BigDecimal(priceRule.getUnitPricePerMile());
        // 时长费
        return 3.14;
    }
}
