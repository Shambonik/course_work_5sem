package com.example.bank.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/admin").setViewName("admin/admin_menu");
        registry.addViewController("/email_sent").setViewName("email_sent");
        registry.addViewController("/token_not_found").setViewName("token_not_found");
    }
}
