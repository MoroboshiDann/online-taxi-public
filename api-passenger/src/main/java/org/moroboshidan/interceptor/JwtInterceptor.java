package org.moroboshidan.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.moroboshidan.internalcommon.dto.ResponseResult;
import org.moroboshidan.internalcommon.dto.TokenResult;
import org.moroboshidan.internalcommon.util.JwtUtils;
import org.moroboshidan.internalcommon.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            resultString = "token sign error";
            result = false;
        } catch (TokenExpiredException e) {
            resultString = "token time out";
            result = false;
        } catch (AlgorithmMismatchException e) {
            resultString = "token AlgorithmMismatchException";
            result = false;
        } catch (Exception e) {
            resultString = "token invalid";
            result = false;
        }
        if (tokenResult == null) {
            resultString = "token invalid";
            result = false;
        } else {
            // 从redis中取出token
            String tokenKey = RedisUtils.generateTokenKey(tokenResult.getPhone(), tokenResult.getIdentity());
            String storedToken = stringRedisTemplate.opsForValue().get(tokenKey);
            //
            if (StringUtils.isBlank(storedToken)) {
                // token过期或者不存在
                resultString = "token invalid";
                result = false;
            } else if (!storedToken.trim().equals(token.trim())) {
                resultString = "token invalid";
                result = false;
            }
        }
        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }
        return result;
    }
}
