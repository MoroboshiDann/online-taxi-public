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

    @PostMapping("/add")
    public ResponseResult add(String name) {
        return terminalService.addTerminal(name);
    }
}
