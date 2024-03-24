package org.moroboshidan.remote;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TerminalClient {
    @Value("${amap.key}")
    private String amapKey;
    @Value("${amap.sid}")
    private String amapSid;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建车辆对应的终端
     * @param name 对应车牌号
     * @param desc 对应车辆在数据库中的car id
     * @return
     */
    public ResponseResult addTerminal(String name, String desc) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_ADD_URL)
                .append("?key=").append(amapKey)
                .append("&sid=").append(amapSid)
                .append("&name=").append(name)
                .append("&desc=").append(desc);
        log.info("url: " + url.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        JSONObject result = JSONObject.fromObject(forEntity.getBody());
        String tid = result.getJSONObject("data").getString("tid");
        return ResponseResult.success(new TerminalResponse(tid));
    }
}
