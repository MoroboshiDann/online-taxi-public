package org.moroboshidan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moroboshidan.internalcommon.dto.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MoroboshiDan
 * @since 2024-03-25
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo> {

}
