package org.moroboshidan.service;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
