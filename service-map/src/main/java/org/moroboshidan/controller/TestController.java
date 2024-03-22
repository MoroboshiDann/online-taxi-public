package org.moroboshidan.controller;

import lombok.extern.slf4j.Slf4j;
import org.moroboshidan.internalcommon.dto.DicDistrict;
import org.moroboshidan.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private DicDistrictMapper dicDistrictMapper;
    @GetMapping("/test")
    public String test() {
        return "test service map";
    }

    @GetMapping("/test-map")
    public String testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("address_code", "110000");
        List<DicDistrict> dicDistricts = dicDistrictMapper.selectByMap(map);
        for (DicDistrict dicDistrict : dicDistricts) {
            System.out.println(dicDistrict.toString());
        }
        return "test map";
    }
}
