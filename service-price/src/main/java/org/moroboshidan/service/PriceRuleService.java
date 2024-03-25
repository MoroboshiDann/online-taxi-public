package org.moroboshidan.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.PriceRule;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.PriceRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceRuleService {
    @Autowired
    private PriceRuleMapper priceRuleMapper;
    /**
     * @description: 添加计价规则
     * @param priceRule
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/25 14:10
     */
    public ResponseResult add(PriceRule priceRule) {
        // 查询当前计价规则是否已经存在，如果存在就更新版本号，否则置为1
        // 由于允许不同版本的计价规则同时存在于数据库中，所以仅仅依靠主键是无法定位一条记录的
        LambdaQueryWrapper<PriceRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PriceRule::getCityCode, priceRule.getCityCode());
        queryWrapper.eq(PriceRule::getVehicleType, priceRule.getVehicleType());
        queryWrapper.orderByDesc(PriceRule::getFareVersion);
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (!priceRules.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_ALREADY_EXISTS.getCode(), CommonStatusEnum.PRICE_RULE_ALREADY_EXISTS.getValue());
        }
        priceRule.setFareVersion(1);
        // 拼接fare type字段
        String fareType = priceRule.getCityCode() + "$" + priceRule.getVehicleType();
        priceRule.setFareType(fareType);
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success("");
    }

    public ResponseResult update(PriceRule priceRule) {
        // 查询当前计价规则是否已经存在，如果存在就更新版本号，否则置为1
        // 由于允许不同版本的计价规则同时存在于数据库中，所以仅仅依靠主键是无法定位一条记录的
        LambdaQueryWrapper<PriceRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PriceRule::getCityCode, priceRule.getCityCode());
        queryWrapper.eq(PriceRule::getVehicleType, priceRule.getVehicleType());
        queryWrapper.orderByDesc(PriceRule::getFareVersion);
        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        int fareVersion = 1;
        if (!priceRules.isEmpty()) {
            PriceRule lastPriceRule = priceRules.get(0);
            if (lastPriceRule.equals(priceRule)) {
                return ResponseResult.fail(CommonStatusEnum.PRICE_RULE_DID_NOT_CHANGE.getCode(), CommonStatusEnum.PRICE_RULE_DID_NOT_CHANGE.getValue());
            }
            fareVersion = lastPriceRule.getFareVersion() + 1;
        }
        priceRule.setFareVersion(fareVersion);
        priceRule.setFareType(priceRule.getCityCode() + "$" + priceRule.getVehicleType());
        priceRuleMapper.insert(priceRule);
        return ResponseResult.success("");
    }
}
