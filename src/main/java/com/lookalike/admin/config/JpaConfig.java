package com.lookalike.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration//@WebMvcTest 테스트할 때 스캔하지 않는다
@EnableJpaAuditing//JPA Auditing
public class JpaConfig {
}
