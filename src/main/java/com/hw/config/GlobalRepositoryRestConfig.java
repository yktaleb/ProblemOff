package com.hw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class GlobalRepositoryRestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                .addMapping("/**")
                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .exposedHeaders("X-Auth-Token")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
                .allowedHeaders("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
                .exposedHeaders("X-Auth-Token","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
                .allowCredentials(false).maxAge(3600);
    }

}