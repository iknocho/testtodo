package com.lookalike.admin.service;


import com.lookalike.admin.domain.posts.Posts;
import com.lookalike.admin.domain.posts.PostsRepository;
import com.lookalike.admin.web.dto.PostsListResponseDto;
import com.lookalike.admin.web.dto.PostsResponseDto;
import com.lookalike.admin.web.dto.PostsSaveRequestDto;
import com.lookalike.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor//@Autowired역활을 한다. 생성자로 Bean 객체를 주입
//final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성해준다
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    //***update에 쿼리 날리는 부분이 없는 이유*** JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경)
    //Spring Data Jpa 사용시 트랜잭션안에서 데이터베이스에서 데이터를 가져오면
    //이 데이터는 영속성 컨텍스트가 유지된다. 즉 이 상태에서 불러온 해당 데이터의 값을 변경하면
    //트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                    .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
}

