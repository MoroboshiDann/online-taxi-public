package org.moroboshidan.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),
    SUCCESS(1, "success"),
    FAIL(0, "fail"),
    TOKEN_ERR(1199, "token错误"),
    USER_NOT_EXISTS(1200, "当前用户不存在"),
    PRICE_RULE_EMPTY(1300, "计价规则不存在"),
    MAP_DISTRICT_ERR(1400, "请求地图错误"),
    DRIVER_CAR_BIND_NOT_EXISTS(1500, "司机车辆绑定关系不存在"),
    DRIVER_CAR_BIND_EXISTS(1501, "司机车辆绑定关系已存在"),
    DRIVER_NOT_EXISTS(1502, "司机不存在"),
    CAR_NOT_EXISTS(1503, "车辆不存在"),
    DRIVER_ALREADY_BIND(1504, "司机已经被绑定"),
    CAR_ALREADY_BIND(1505, "车辆已经被绑定")
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
