package org.moroboshidan.internalcommon.util;

public class RedisUtils {
    public static String verificationCodePrefix = "passenger-verification";
    public static String tokenPrefix = "token";
    /**
     * 根据手机号生成验证码在redis中的key
     * @param
     * @return
     * @throws
     *
     */
    public static String generateKey(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 生成token在redis中的key
     * @param
     * @return
     * @throws
     *
     */
    public static String generateTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + "-" + phone + "-" + identity + "-" + tokenType;
    }
}
