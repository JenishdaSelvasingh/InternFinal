package com.example.demo.Config;

import com.example.demo.JWTUtil.JWTUtils;
import com.example.demo.enums.AccessTag;
import com.example.demo.enums.AccessTags;
import io.jsonwebtoken.Claims;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class JWTInterceptor implements HandlerInterceptor {
    private final JWTUtils jwtUtils;
    private final LoginReqMeta loginReqMeta;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        } else {
            AccessTags accessTags = (AccessTags)handlerMethod.getMethodAnnotation(AccessTags.class);
            if (accessTags == null) {
                return true;
            } else {
                String auth = request.getHeader("authorization");
                if (auth != null && !auth.isEmpty()) {
                    Claims claims;
                    try {
                        claims = this.jwtUtils.verify(auth);
                    } catch (Exception var13) {
                        response.setStatus(401);
                        return false;
                    }

                    System.out.println(claims.get("accessTags"));
                    List<String> userTags = (List)claims.get("accessTags");
                    if (userTags != null && !userTags.isEmpty()) {
                        AccessTag[] var9 = accessTags.value();
                        int var10 = var9.length;

                        for(int var11 = 0; var11 < var10; ++var11) {
                            AccessTag requiredTag = var9[var11];
                            if (userTags.contains(requiredTag.name())) {
                                return true;
                            }
                        }

                        response.setStatus(403);
                        return false;
                    } else {
                        response.setStatus(403);
                        return false;
                    }
                } else {
                    response.setStatus(401);
                    return false;
                }
            }
        }
    }

    public JWTInterceptor(final JWTUtils jwtUtils, final LoginReqMeta loginReqMeta) {
        this.jwtUtils = jwtUtils;
        this.loginReqMeta = loginReqMeta;
    }
}
