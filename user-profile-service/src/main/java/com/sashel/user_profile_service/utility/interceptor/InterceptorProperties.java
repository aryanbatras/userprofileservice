package com.sashel.user_profile_service.utility.interceptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "interceptor")
public class InterceptorProperties {

    private List<RouteAccessRule> buyerAccessRules;
    private List<RouteAccessRule> adminAccessRules;
    private List<RouteAccessRule> sellerAccessRules;

    @Data
    public static class RouteAccessRule {
        private String pattern;
        private String method;
        private Set<String> roles;
    }
}
