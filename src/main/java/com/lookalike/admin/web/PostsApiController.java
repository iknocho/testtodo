package com.lookalike.admin.web;

import com.lookalike.admin.service.PostsService;
import com.lookalike.admin.web.dto.PostsResponseDto;
import com.lookalike.admin.web.dto.PostsSaveRequestDto;
import com.lookalike.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//@Autowired역활을 한다. 생성자로 Bean 객체를 주입
//final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성해준다
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

}
