package org.moroboshidan.remote;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.response.TerminalResponse;
import org.moroboshidan.internalcommon.response.TerminalSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/aroundsearch")
    ResponseResult<List<TerminalResponse>> terminalAroundSearch(@RequestParam String center, @RequestParam Integer radius);

    @PostMapping("/terminal-search")
    ResponseResult<TerminalSearchResponse> terminalSearch(@RequestParam String tid, @RequestParam Long startime, @RequestParam Long endtime);
}
