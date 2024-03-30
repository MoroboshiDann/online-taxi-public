package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.internalcommon.response.TerminalSearchResponse;
import org.moroboshidan.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;

    /**
     * 创建车辆对应的终端
     * @param name 对应车牌号
     * @param desc 对应车辆在数据库中的car id
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(String name, String desc) {
        return terminalService.addTerminal(name, desc);
    }

    /**
     * 地图周边搜索，给出搜素中心的经纬度和半径
     * @param center
     * @param radius
     * @return
     */
    @PostMapping("/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundSearch(@RequestParam String center, @RequestParam Integer radius) {
        return terminalService.aroundSearch(center, radius);
    }

    /**
     * @description: 查询轨迹结果，获取行驶路程和时间
     * @param tid
     * @param starttime
     * @param endtime
     * @return: org.moroboshidan.internalcommon.dto.ResponseResult
     * @author: MoroboshiDan
     * @time: 2024/3/30 15:48
     */
    @PostMapping("/terminal-search")
    public ResponseResult<TerminalSearchResponse> terminalSearch(String tid, Long starttime, Long endtime) {
        return terminalService.terminalSearch(tid, starttime, endtime);
    }
}
