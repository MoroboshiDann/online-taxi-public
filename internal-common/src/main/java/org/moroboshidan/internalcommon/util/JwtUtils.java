package org.moroboshidan.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.moroboshidan.internalcommon.constant.TokenConstants;
import org.moroboshidan.internalcommon.dto.TokenResult;

import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // salt
    private static final String SIGN = "CPFmoro!@#";
    private static final String JWT_KEY_PHONE = "passengerPhone";
    private static final String JWT_KEY_IDENTITY = "identity";

    private static final String JWT_TOKEN_TYPE = "tokenType";
    // 生成token
    public static String generateToken(String passengerPhone, String identity, String tokenType) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity); // 添加identity字段，防止出现司机和乘客手机号重复的情况
        map.put(JWT_TOKEN_TYPE, tokenType);

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

    public static void main(String[] args) {
        String token = generateToken("15212311231", "passenger", TokenConstants.ACCESS_TOKEN_TYPE);
        System.out.println(token);
        TokenResult tokenResult = parseToken(token);
        System.out.println(tokenResult.getPhone());
        System.out.println(tokenResult.getIdentity());
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
