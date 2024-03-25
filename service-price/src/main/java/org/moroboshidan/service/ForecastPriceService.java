package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.ForecastPriceDTO;
import org.moroboshidan.internalcommon.response.DirectionResponse;
import org.moroboshidan.internalcommon.response.ForecastPriceResponse;
import org.moroboshidan.internalcommon.util.BigDecimalUtils;
import org.moroboshidan.mapper.PriceRuleMapper;
import org.moroboshidan.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public ResponseResult<ForecastPriceResponse> forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        log.info("调用地图服务，查询距离和时长...");
        ResponseResult<DirectionResponse> drivingResult = serviceMapClient.driving(new ForecastPriceDTO(forecastPriceDTO.getDepLongitude(), forecastPriceDTO.getDepLatitude(), forecastPriceDTO.getDestLongitude(), forecastPriceDTO.getDestLatitude(), null, null));
        log.info(drivingResult.getData().toString());
        DirectionResponse directionResponse = drivingResult.getData();
        log.info("读取计价规则");
        LambdaQueryWrapper<PriceRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PriceRule::getCityCode, forecastPriceDTO.getCityCode());
        queryWrapper.eq(PriceRule::getVehicleType, forecastPriceDTO.getVehicleType());
        queryWrapper.orderByDesc(PriceRule::getFareVersion);
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_EMPTY.getCode(), CommonStatusEnum.PRICE_RULE_EMPTY.getValue());
        }
        log.info("根据距离、时长、计价规则计算预估价格");
        double price = getPrice(directionResponse.getDistance(), directionResponse.getDuration(), priceRules.get(0));
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse(forecastPriceDTO.getCityCode(), forecastPriceDTO.getVehicleType(), price);
        return ResponseResult.success(forecastPriceResponse);
    }

    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        double price = 0.0;
        // 起步价
        price = BigDecimalUtils.add(price, priceRule.getStartFare());
        // 里程费
        // 总里程m -> km
        try {
            double distanceKilo = BigDecimalUtils.divide(distance, 1000);
            double distanceSubtract = BigDecimalUtils.subtract(distanceKilo, (double) priceRule.getStartMile());
            // 计算超出起步里程的部分km
            double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
            // 计程单价
            double mileFare = BigDecimalUtils.multiply(mile, priceRule.getUnitPricePerMile());
            price = BigDecimalUtils.add(price, mileFare);
            // 时长费
            BigDecimal unitPricePerMinute = BigDecimal.valueOf(priceRule.getUnitPricePerMinute());
            double minutes = BigDecimalUtils.divide(duration, 60);
            double timeFare = BigDecimalUtils.multiply(minutes, priceRule.getUnitPricePerMinute());
            price = BigDecimalUtils.add(price, timeFare);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return price;
    }
}
