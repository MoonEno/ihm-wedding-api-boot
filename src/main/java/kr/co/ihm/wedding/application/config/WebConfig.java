package kr.co.ihm.wedding.application.config; /**
 * (주)오픈잇 | https://www.openit.co.kr
 * Copyright (c)2006-2021, openit Inc.
 * All right reserved.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.ihm.wedding.application.interceptor.AuthenticationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * @author hdkim
 * @desc WebConfig 세팅
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Bean
    @ConditionalOnMissingBean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
    
    /**
     * url Interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/v1/cm/login/auth")
                .excludePathPatterns("/swagger/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/resources/**");
    }
    
    /**
     * XSS Filter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(escapingConverter());
    }

    @Bean
    public HttpMessageConverter<?> escapingConverter() {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter escapingConverter = new MappingJackson2HttpMessageConverter();
        escapingConverter.setObjectMapper(objectMapper);

        return escapingConverter;
    }
}
