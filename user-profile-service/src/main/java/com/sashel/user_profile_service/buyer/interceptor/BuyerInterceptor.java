package com.sashel.user_profile_service.buyer.interceptor;

import com.sashel.user_profile_service.utility.interceptor.InterceptorProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BuyerInterceptor implements HandlerInterceptor {

    @Autowired
    private InterceptorProperties interceptorProperties;

    private AntPathMatcher matcher;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        var requestRole = request.getHeader("X-User-Role");
        var requestURI = request.getRequestURI();
        var requestMethod = request.getMethod();
        matcher = new AntPathMatcher();

        for (InterceptorProperties.RouteAccessRule accessRule : interceptorProperties.getBuyerAccessRules()) {

            if (matcher.match(accessRule.getPattern(), requestURI)
                    && requestMethod.equalsIgnoreCase(accessRule.getMethod())) {

                if (!accessRule.getRoles().contains(requestRole.toUpperCase())) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Access Denied");
                    return false;
                }

                return true;
            }
        }

        return true; // Let it go if no accessRule matches

    }

}
