package com.lookalike.admin.web.dto;

import com.lookalike.admin.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//페이지와 관련한 컨트롤러
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        //Model객체: 서버 템플릿 엔지에서 사용할 수 있는 객체를 저장가능하다
        //postService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다
        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
        //mustach stater 덕분에 문자열을 반환할 때
        //자동으로 src/main/resourecs/templates/index.mustache로 전환되어
        //View Resolver가 처리한다
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    //Model 객체????
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

}
