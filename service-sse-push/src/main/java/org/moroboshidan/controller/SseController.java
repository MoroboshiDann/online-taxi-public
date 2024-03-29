package org.moroboshidan.controller;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.util.SseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class SseController {
    private static Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    /**
     * @param userId
     * @param identity
     * @description: 建立连接
     * @return: org.springframework.web.servlet.mvc.method.annotation.SseEmitter
     * @author: MoroboshiDan
     * @time: 2024/3/29 18:13
     */
    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam Long userId, @RequestParam String identity) {
        log.info("user id: " + userId + "\nidentity: " + identity);
        SseEmitter sseEmitter = new SseEmitter(0l);
        sseEmitterMap.put(SseUtils.generateSseKey(userId, identity), sseEmitter);
        return sseEmitter;
    }

    /**
     * @param userId
     * @param identity
     * @param content
     * @description: 给指定用户发送消息
     * @return: java.lang.String
     * @author: MoroboshiDan
     * @time: 2024/3/29 22:01
     */
    @GetMapping("/push")
    public String push(@RequestParam Long userId, @RequestParam String identity, @RequestParam String content) {
        try {
            String sseKey = SseUtils.generateSseKey(userId, identity);
            sseEmitterMap.get(sseKey).send(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "给用户：" + userId + "，发送了消息：" + content;
    }

    /**
     * @param userId
     * @param identity
     * @description: 清除map中对应的用户
     * @return: java.lang.String
     * @author: MoroboshiDan
     * @time: 2024/3/29 22:02
     */
    @GetMapping("/close")
    public String close(@RequestParam Long userId, @RequestParam String identity) {
        sseEmitterMap.remove(SseUtils.generateSseKey(userId, identity));
        return "close 成功";
    }
}
