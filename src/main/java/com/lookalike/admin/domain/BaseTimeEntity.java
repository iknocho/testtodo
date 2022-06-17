package com.lookalike.admin.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//모든 Entity의 상위 클래스 Entity들의 createDate, modifiedDate 자동으로 관리

@Getter
@MappedSuperclass //JPA Entity클래스들이 BaseTimeEntity를 상속할경우
//필드들 createdDate,modifiedDate 칼럼으로 인식하도록 한다
@EntityListeners(AuditingEntityListener.class)
//Auditing 기능을 포함시킨다.  ***Application에 @EnableJpaAuditing 추가***
public abstract class BaseTimeEntity {

    @CreatedDate//Entity생성되어 저장될때 시간이 자동 저장된다
    private LocalDateTime createdDate;

    @LastModifiedDate//조회한 Entity의 값을 변경할 때 시간이 자동저장된다
    private LocalDateTime modifiedDate;

}
