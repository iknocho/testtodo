package com.lookalike.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//해당 컨트롤러는 특별히 스프링 환경이 필요없으므로 @SpringBootTest 없이 테스트 코드 작성

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test//이 테스트가 없었으면 SpringBootTest사용하지 않아도 된다 (속도문제)
    public void profile인증없이_호출() throws Exception{
        String expected = "default";

        ResponseEntity<String> response = restTemplate.getForEntity("/profile",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    public void real_profile이_조회(){
        //given 테스트 기본적으로 세팅하는 값
        String expectedProfile="real";
        MockEnvironment env=new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);
        //when 테스트를 하기 위한 조건을 지정

        String profile = controller.profile();

        //then 테스트 후 우리가 예상한대로 동작하는지 확인

        assertThat(profile).isEqualTo(expectedProfile);

    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회됨(){
        //given
        String expectedProfile= "oauth";
        MockEnvironment env= new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_profile이_없으면_default가_조회(){
        //given
        String expectedProfile="default";
        MockEnvironment env= new MockEnvironment();
        ProfileController controller =new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}
