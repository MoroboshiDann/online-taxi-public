package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {
    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult addTerminal(String name) {
        return terminalClient.addTerminal(name);
    }
}
