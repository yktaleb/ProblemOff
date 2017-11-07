package com.hw.util.bean;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class Beans {

    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .exposedHeaders("X-Auth-Token");
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//                        .allowedHeaders("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
//                        .exposedHeaders("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
//                        .allowCredentials(false).maxAge(3600);
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
