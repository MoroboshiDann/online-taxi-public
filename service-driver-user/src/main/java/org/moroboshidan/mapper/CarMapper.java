package org.moroboshidan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.moroboshidan.internalcommon.dto.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MoroboshiDan
 * @since 2024-03-23
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

}
