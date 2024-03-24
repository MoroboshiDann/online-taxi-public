package org.moroboshidan.remote;

import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.PointDTO;
import org.moroboshidan.internalcommon.request.PointRequest;
import org.moroboshidan.internalcommon.response.TrackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class TrackClient {
    @Value("${amap.key}")
    private String amapKey;
    @Value("${amap.sid}")
    private String amapSid;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult addTrack(String tid) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACK_ADD_URL)
                .append("?key=").append(amapKey)
                .append("&sid=").append(amapSid)
                .append("&tid=").append(tid);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        JSONObject result = JSONObject.fromObject(forEntity.getBody());
        JSONObject data = result.getJSONObject("data");
        String trid = data.getString("trid");
        String trname = "";
        if (data.has("trname")) {
            trname = data.getString("trname");
        }
        return ResponseResult.success(new TrackResponse(trid, trname));
    }


    public ResponseResult uploadTrackPoints(PointRequest pointRequest) {
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACK_POINTS_ADD_URL)
                .append("?key=").append(amapKey)
                .append("&sid=").append(amapSid)
                .append("&tid=").append(pointRequest.getTid())
                .append("&trid=").append(pointRequest.getTrid())
                .append("&points=%5B"); // [
        for (PointDTO point : pointRequest.getPoints()) {
            url.append("%7B%22locate%22%3A%22").append(point.getLocate());
            url.append("%22%2C%22locatetime%22%3A").append(point.getLocateTime());
            url.append("%7D");
        }
        url.append("%5D"); // ]
        JSONObject result = JSONObject.fromObject(restTemplate.postForEntity(URI.create(url.toString()), null, String.class).getBody());
        JSONObject data = result.getJSONObject("data");


        return ResponseResult.success();
    }
}
