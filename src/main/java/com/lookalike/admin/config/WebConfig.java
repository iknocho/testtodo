package com.lookalike.admin.config;

import com.lookalike.admin.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//LoginUserArgumentResolver 조건에맞는 메소드의 파라미터로 구현체의 지정한 값을 해당메서드의 파라미터로 넘겨준다
//HandlerMethodArgument 인터페이스를 이용해만 만든 어노테이션을 스프링이 인식할 수 있게한다
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
