package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapDicDistrictClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${amap.key}")
    private String amapkey;
    public String dicDistrict(String keywords) {
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
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        return forEntity.getBody();
    }
}
