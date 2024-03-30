package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.internalcommon.response.TerminalSearchResponse;
import org.moroboshidan.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {
    @Autowired
    private TerminalClient terminalClient;

    /**
     * 创建车辆对应的终端
     * @param name 对应车牌号
     * @param desc 对应车辆在数据库中的car id
     * @return
     */
    public ResponseResult addTerminal(String name, String desc) {
        return terminalClient.addTerminal(name, desc);
    }

    /**
     * 地图周边搜索，给出搜素中心的经纬度和半径
     * @param center
     * @param radius
     * @return
     */
    public ResponseResult<List<TerminalResponse>> aroundSearch(String center, Integer radius) {
        return terminalClient.aroundSearch(center, radius);
    }

    public ResponseResult<TerminalSearchResponse> terminalSearch(String tid, Long starttime, Long endtime) {
        return terminalClient.terminalSearch(tid, starttime, endtime);
    }
}
