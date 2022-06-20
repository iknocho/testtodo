package com.lookalike.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//규모가 있는 프로젝트의 데이터 조회에서 FK의 조인, 복잡한 조건으로 인해
//Entity 클래스만으로 처리하기 어려울때 조회용 프레임워크 querydsl 시용을 통해
//조회하고 SpringDataJpa를 통해 등록/수정/삭제 진
public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p order by p.id DESC")
    List<Posts> findAllDesc();
}
