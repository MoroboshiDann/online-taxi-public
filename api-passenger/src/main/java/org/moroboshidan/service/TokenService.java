package org.moroboshidan.service;

import org.apache.commons.lang.StringUtils;
import org.moroboshidan.internalcommon.constant.CommonStatusEnum;
import org.moroboshidan.internalcommon.constant.IdentityConstant;
import org.moroboshidan.internalcommon.constant.TokenConstants;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.TokenResult;
import org.moroboshidan.internalcommon.response.TokenResponse;
import org.moroboshidan.internalcommon.util.JwtUtils;
import org.moroboshidan.internalcommon.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public ResponseResult refreshToken(String refreshTokenSrc) {
        // 解析token
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERR.getCode(), CommonStatusEnum.TOKEN_ERR.getValue());
        }
        String passengerPhone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        // 读取redis中的refresh token，进行对比
        // 生成key
        String refreshTokenKey = RedisUtils.generateTokenKey(passengerPhone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenInRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        if ((StringUtils.isBlank(refreshTokenInRedis)) || (!refreshTokenInRedis.equals(refreshTokenSrc))) {
            // token过期或者不存在
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERR.getCode(), CommonStatusEnum.TOKEN_ERR.getValue());
        }
        // 生成双token
        String accessToken = JwtUtils.generateToken(passengerPhone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(passengerPhone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        // 存入redis
        String accessTokenKey = RedisUtils.generateTokenKey(passengerPhone, identity, IdentityConstant.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);
        return ResponseResult.success(new TokenResponse(accessToken, refreshToken));
    }
}
