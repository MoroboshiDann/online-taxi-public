package org.moroboshidan.controller;

import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceFromMapService serviceFromMapService;
    /**
     * @description 添加service，高德地图猎鹰服务中，service管理terminal即车辆，车辆拥有轨迹
     * @param * @param null:
     * @return * @return null
     * @throws
     *
     */
    @PostMapping("/add")
    public ResponseResult add(String name) {
        return serviceFromMapService.add(name);
    }
}
