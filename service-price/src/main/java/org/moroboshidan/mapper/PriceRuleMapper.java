package org.moroboshidan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.moroboshidan.internalcommon.dto.PriceRule;

@Mapper
public interface PriceRuleMapper extends BaseMapper<PriceRule> {
}
