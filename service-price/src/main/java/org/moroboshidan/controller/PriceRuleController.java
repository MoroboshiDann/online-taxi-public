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
    /**
     * @description: 插入计价规则
     * @param priceRule
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:41
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule) {
        return priceRuleService.add(priceRule);
    }

    /**
     * @description: 修改计价规则
     * @param priceRule
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:41
     */
    @PostMapping("/update")
    public ResponseResult update(@RequestBody PriceRule priceRule) {
        return priceRuleService.update(priceRule);
    }

    /**
     * @description: 获取当前计价规则的最新版本
     * @param fareType
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:41
     */
    @GetMapping("/get-newest")
    public ResponseResult getNewestPriceRule(@RequestParam String fareType) {
        return priceRuleService.getNewestPriceRule(fareType);
    }

    /**
     * @description: 检查计价规则是否是最新版本
     * @param fareType
     * @param fareVersion
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:42
     */
    @GetMapping("/is-newest")
    public ResponseResult isNewest(@RequestParam String fareType, @RequestParam int fareVersion) {
        return priceRuleService.isNewest(fareType, fareVersion);
    }

    /**
     * @description: 根据城市编码和车辆类型检查当前城市是否有计价规则
     * @param priceRule
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 20:42
     */
    @PostMapping("/if-exists")
    public ResponseResult ifExists(@RequestBody PriceRule priceRule) {
        return priceRuleService.ifExists(priceRule);
    }
}
