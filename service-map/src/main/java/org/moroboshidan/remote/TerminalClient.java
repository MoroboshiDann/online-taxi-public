package org.moroboshidan.remote;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
        return ResponseResult.success(new TerminalResponse(tid, null));
    }

    /**
     * 根据中心经纬度和半径，搜索周边车辆(终端)
     * @param center
     * @param radius
     * @return
     */
    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_AROUND_SEARCH_URL)
                .append("?key=").append(amapKey)
                .append("&sid=").append(amapSid)
                .append("&center=").append(center)
                .append("&radius=").append(radius);
        log.info("searching url: " + url);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        JSONObject data = JSONObject.fromObject(forEntity.getBody())
                                        .getJSONObject("data");
        log.info("data: " + data);
        JSONArray results = data.getJSONArray("results");
        log.info("searching results:" + results);
        List<TerminalResponse> list = new ArrayList<>();
        for (Object result : results) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            list.add(new TerminalResponse(jsonObject.getString("tid"), jsonObject.getLong("desc")));
        }
        return ResponseResult.success(list);
    }
}
