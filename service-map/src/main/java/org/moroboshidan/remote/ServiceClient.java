package org.moroboshidan.remote;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ServiceClient {
    @Value("${amap.key}")
    private String amapKey;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建service
     * @param name
     * @return
     */
    public ResponseResult add(String name) {
        // 组装参数
        // https://restapi.amap.com/v3/direction/driving?origin=&destination=&extensions=all
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.SERVICE_ADD_URL);
        urlBuild.append("?key=").append(amapKey);
        urlBuild.append("&name=").append(name);
        log.info(urlBuild.toString());
        // 调用高德接口
        ResponseEntity<String> forEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        // 解析接口
        JSONObject result = JSONObject.fromObject(forEntity.getBody());
        String sid = (String) result.getJSONObject("data").get("sid");
        return ResponseResult.success(sid);
    }
}
