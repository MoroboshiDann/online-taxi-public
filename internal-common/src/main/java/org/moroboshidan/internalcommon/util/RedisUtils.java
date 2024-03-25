package org.moroboshidan.internalcommon.util;

public class RedisUtils {
    public static String verificationCodePrefix = "verification-code-";
    public static String tokenPrefix = "token-";
    public static String blacklistedDevicePrefix = "blacklisted-device-";
    /**
     * 根据手机号生成验证码在redis中的key
     * @param
     * @return
     * @throws
     *
     */
    public static String generateKey(String phone, String identity) {
        return verificationCodePrefix + identity + "-" + phone;
    }

    /**
     * 生成token在redis中的key
     * @param
     * @return
     * @throws
     *
     */
    public static String generateTokenKey(String phone, String identity, String tokenType) {
        return tokenPrefix + identity + "-" + phone + "-" + tokenType;
    }
}
