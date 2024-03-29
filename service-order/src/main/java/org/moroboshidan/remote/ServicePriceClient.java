package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.PriceRuleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/price-rule/is-newest")
    ResponseResult<Boolean> isNewest(@RequestBody PriceRuleRequest priceRuleRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/price-rule/if-exists")
    ResponseResult<Boolean> isPriceRuleExists(@RequestBody PriceRule priceRule);
}
