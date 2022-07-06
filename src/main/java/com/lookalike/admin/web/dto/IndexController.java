package com.lookalike.admin.web.dto;
import javax.servlet.http.HttpSession;

import com.lookalike.admin.config.auth.LoginUser;
import com.lookalike.admin.config.auth.dto.SessionUser;
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
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        //Model객체: 서버 템플릿 엔지에서 사용할 수 있는 객체를 저장가능하다
        //postService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다
        model.addAttribute("posts",postsService.findAllDesc());

        //**SessionUser user=(SessionUser) httpSession.getAttribute("user");
        //위 코드 한줄을 줄이기 위해서 @LoginUser, LoginUserArgumentResolver,webConig 설정이 필요



        //CustomOAuth2UserService에서 로그인 성고시 세션에 SessionUser를 저장하도록 구성

        if(user!=null){
            model.addAttribute("userName",user.getName());
        //세션에 저장된 값이 있을때만 model애 userName으로 등록
        //세션에 저장된 값이 없으면 model에 값이 없으므로 login버튼이 보이게 된다.
        }

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
