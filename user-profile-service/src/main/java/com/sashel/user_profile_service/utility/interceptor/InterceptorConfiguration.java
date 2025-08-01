package com.sashel.user_profile_service.utility.interceptor;

import com.sashel.user_profile_service.admin.interceptor.AdminInterceptor;
import com.sashel.user_profile_service.buyer.interceptor.BuyerInterceptor;
import com.sashel.user_profile_service.seller.interceptor.SellerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    BuyerInterceptor buyerInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;

    @Autowired
    SellerInterceptor sellerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(buyerInterceptor);
        registry.addInterceptor(adminInterceptor);
        registry.addInterceptor(sellerInterceptor);
    }

}
