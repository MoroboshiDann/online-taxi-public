package org.moroboshidan.service;

import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DicDistrictService {
    @Value("${amap.key}")
    private String amapkey;
    public ResponseResult initDicDistrict(String keywords) {
        // 拼装请求的url，并请求
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL)
                .append("?")
                .append("keywords=")
                .append(keywords)
                .append("&")
                .append("subdistrict=3")
                .append("&")
                .append("key=")
                .append(amapkey);

        // 解析结果

        // 插入数据库
        return ResponseResult.success();
    }
}
