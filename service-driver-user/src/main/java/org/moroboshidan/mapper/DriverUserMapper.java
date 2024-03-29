package org.moroboshidan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.moroboshidan.internalcommon.dto.DriverUser;

@Mapper
public interface DriverUserMapper extends BaseMapper<DriverUser> {
    int selectDriverCountByCityCode(@Param("cityCode") String arg);
}
