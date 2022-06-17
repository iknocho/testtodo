package com.lookalike.admin.domain.posts;

import com.lookalike.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Setter를 쓰지 않는 이유 해당클래스의 인스턴스 값들이 언제 어디서 변해야하는지
//코드상으로 명확하게 구분할 수 없어서 Entity클래스에는 Setter를 만들지 않고 목적과
//의도를 나타낼수 있는 메소드를 추가한다
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private  String author;

    @Builder
    public Posts(String title,String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }
}
