package org.moroboshidan.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {
    /**
     * 验证码
     */
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),
    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    /**
     * token
     */
    TOKEN_ERR(1199, "token错误"),
    /**
     * 用户
     */
    USER_NOT_EXISTS(1200, "当前用户不存在"),
    /**
     * 计价规则
     */
    PRICE_RULE_EMPTY(1300, "计价规则不存在"),
    PRICE_RULE_ALREADY_EXISTS(1301, "计价规则已存在"),
    PRICE_RULE_DID_NOT_CHANGE(1302, "计价规则没有变化"),
    PRICE_RULE_CHANGED(1303, "计价规则有变化"),
    /**
     * 地图
     */
    MAP_DISTRICT_ERR(1400, "请求地图错误"),
    /**
     * 司机车辆
     */
    DRIVER_CAR_BIND_NOT_EXISTS(1500, "司机车辆绑定关系不存在"),
    DRIVER_CAR_BIND_EXISTS(1501, "司机车辆绑定关系已存在"),
    DRIVER_NOT_EXISTS(1502, "司机不存在"),
    CAR_NOT_EXISTS(1503, "车辆不存在"),
    DRIVER_ALREADY_BIND(1504, "司机已经被绑定"),
    CAR_ALREADY_BIND(1505, "车辆已经被绑定"),
    /**
     * 订单
     */
    ORDER_IN_PROCESS(1600, "有正在进行的订单"),
    DEVICE_IS_BLACKLISTED(1601, "该设备短期内超过了下单次数"),
    NOT_IN_SERVICE_REGION(1602, "当前城市未开通服务"),
    ;

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}