package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {
    @Autowired
    private PriceRuleService priceRuleService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule) {
        return priceRuleService.add(priceRule);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody PriceRule priceRule) {
        return priceRuleService.update(priceRule);
    }


    @GetMapping("/get-newest")
    public ResponseResult getNewestPriceRule(@RequestParam String fareType) {
        return priceRuleService.getNewestPriceRule(fareType);
    }

    @GetMapping("/is-newest")
    public ResponseResult isNewest(@RequestParam String fareType, @RequestParam int fareVersion) {
        return priceRuleService.isNewest(fareType, fareVersion);
    }
}
