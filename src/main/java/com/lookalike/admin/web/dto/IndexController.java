package com.lookalike.admin.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//페이지와 관련한 컨트롤러
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
        //mustach stater 덕분에 문자열을 반환할 때
        //자동으로 src/main/resourecs/templates/index.mustache로 전환되어
        //View Resolver가 처리한다
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
