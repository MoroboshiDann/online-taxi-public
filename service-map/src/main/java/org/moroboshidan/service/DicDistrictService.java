package org.moroboshidan.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.moroboshidan.internalcommon.constant.AmapConfigConstants;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.DicDistrict;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.mapper.DicDistrictMapper;
import org.moroboshidan.remote.MapDicDistrictClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicDistrictService {
    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;
    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {
        // 请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrictResult);
        // 解析结果
        JSONObject dicDistrictObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatusEnum.MAP_DISTRICT_ERR.getCode(), CommonStatusEnum.MAP_DISTRICT_ERR.getValue());
        }
        // todo 插入数据库，待优化
        JSONArray countryArray = dicDistrictObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int country = 0; country < countryArray.size(); ++country) {
            JSONObject countryObject = countryArray.getJSONObject(country);
            String countryAddressCode = countryObject.getString(AmapConfigConstants.ADDRESS_CODE);
            String countryAddressName = countryObject.getString(AmapConfigConstants.ADDRESS_NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryObject.getString(AmapConfigConstants.LEVEL);
            insertDicDistrict(countryAddressCode, countryAddressName, countryParentAddressCode, countryLevel);
            JSONArray provinceArray = countryObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int province = 0; province < provinceArray.size(); province++) {
                JSONObject provinceObject = provinceArray.getJSONObject(province);
                String provinceAddressCode = provinceObject.getString(AmapConfigConstants.ADDRESS_CODE);
                String provinceAddressName = provinceObject.getString(AmapConfigConstants.ADDRESS_NAME);
                String provinceParentAddressCode = countryAddressCode;
                String provinceLevel = provinceObject.getString(AmapConfigConstants.LEVEL);
                insertDicDistrict(provinceAddressCode, provinceAddressName, provinceParentAddressCode, provinceLevel);
                JSONArray cityArray = provinceObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int city = 0; city < cityArray.size(); city++) {
                    JSONObject cityObject = cityArray.getJSONObject(city);
                    String cityAddressCode = cityObject.getString(AmapConfigConstants.ADDRESS_CODE);
                    String cityAddressName = cityObject.getString(AmapConfigConstants.ADDRESS_NAME);
                    String cityParentAddressCode = provinceAddressCode;
                    String cityLevel = cityObject.getString(AmapConfigConstants.LEVEL);
                    insertDicDistrict(cityAddressCode, cityAddressName, cityParentAddressCode, cityLevel);
                    JSONArray districtArray = cityObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int district = 0; district < districtArray.size(); district++) {
                        JSONObject districtObject = districtArray.getJSONObject(district);
                        String districtAddressCode = districtObject.getString(AmapConfigConstants.ADDRESS_CODE);
                        String districtAddressName = districtObject.getString(AmapConfigConstants.ADDRESS_NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtObject.getString(AmapConfigConstants.LEVEL);
                        if (districtLevel.equals(AmapConfigConstants.STREET)) {
                            continue;
                        }
                        insertDicDistrict(districtAddressCode, districtAddressName, districtParentAddressCode, districtLevel);
                    }
                }
            }
        }
        // 插入数据库
        return ResponseResult.success();
    }

    private void insertDicDistrict(String addressCode, String addressName, String parentAddressCode, String level) {
        dicDistrictMapper.insert(new DicDistrict(addressCode, addressName, parentAddressCode, generateLevel(level)));
    }

    private int generateLevel(String level) {
        int levelInt = 0;
        if (level.equals("province")) {
            levelInt = 1;
        } else if (level.equals("city")) {
            levelInt = 2;
        } else if (level.equals("district")) {
            levelInt = 3;
        }
        return levelInt;
    }
}
