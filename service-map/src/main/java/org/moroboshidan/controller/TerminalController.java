package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
