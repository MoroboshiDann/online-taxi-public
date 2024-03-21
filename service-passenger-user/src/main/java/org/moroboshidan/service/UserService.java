package org.moroboshidan.service;

import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.dto.PassengerUser;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.request.VerificationCodeDTO;
import org.moroboshidan.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOfRegistry(VerificationCodeDTO verificationCodeDTO) {
        System.out.println("user service");
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", verificationCodeDTO.getPassengerPhone());
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        // 判断用户信息是否存在
        if (passengerUsers.size() == 0) {
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setState((byte) 0);
            passengerUser.setPassengerPhone(verificationCodeDTO.getPassengerPhone());
            passengerUser.setGmtCreate(LocalDateTime.now());
            passengerUser.setGmtModified(LocalDateTime.now());
            passengerUserMapper.insert(passengerUser);
        }
        return ResponseResult.success();
    }

    /**
     * 根据手机号查询用户信息
     * @param
     * @return
     * @throws
     *
     */
    public ResponseResult getUserByPhone(String passengerPhone) {
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if (passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(), CommonStatusEnum.USER_NOT_EXISTS.getValue());
        }
        PassengerUser passengerUser = passengerUsers.get(0);
        return ResponseResult.success(passengerUser);
    }
}