package com.lookalike.admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing//JPA auditing 활성화
// @WebMvcTest 테스트할떄 스캔에 @SpringBootApplication을 스캔하는데
// @EnableJpaAuditing 떄문에 에러 발생 이유: 이 어노테이션을 사용하기위해서는 하나의 엔티티가 필요한데 없기 떄문에 사용 불가
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
