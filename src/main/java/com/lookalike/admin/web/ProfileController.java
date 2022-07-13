package com.lookalike.admin.web;

import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//배포 시에 8081을 쓸지, 8082를 쓸지 판단하는 기준이 되는 API

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;

    @GetMapping("/profile")
    public String profile(){
        List<String> profiles= Arrays.asList(env.getActiveProfiles());
        //getActiveProfiles : 현재 진행중인 ActiveProfile을 모두 가져온다
        //real, outh,real-db등이 활성화 되어 있다면 3개가 모두 담겨있다
        //real,real1,real2는 모두 배포에 사용될 profile이라 이중 하나라도 있으면 그 값을 반환한다
        List<String> realProfiles=Arrays.asList("real","real1","real2");
        String defaultProfile = profiles.isEmpty()? "default": profiles.get(0);
        return profiles.stream()
                .filter(realProfiles::contains) //람다식 함수 간결한 표현 s-> realProfiles.contains(s)
                .findAny()
                .orElse(defaultProfile);
    }

}
