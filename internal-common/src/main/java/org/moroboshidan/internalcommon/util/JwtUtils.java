package org.moroboshidan.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.moroboshidan.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // salt
    private static final String SIGN = "CPFmoro!@#";
    private static final String JWT_KEY_PHONE = "passengerPhone";
    private static final String JWT_KEY_IDENTITY = "identity";
    private static final String JWT_TOKEN_TYPE = "tokenType";
    private static final String JWT_TOKEN_TIME = "tokenTime";
    // 生成token
    public static String generateToken(String phone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identity); // 添加identity字段，防止出现司机和乘客手机号重复的情况
        map.put(JWT_TOKEN_TYPE, tokenType);
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());

        JWTCreator.Builder builder = JWT.create();

        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }
    // 解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        String tokenType = verify.getClaim(JWT_TOKEN_TYPE).asString();
        return new TokenResult(phone, identity, tokenType);
    }

    /**
     * 校验token，判断是否异常
     * @param
     * @return
     * @throws
     *
     */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenResult;
    }
}
