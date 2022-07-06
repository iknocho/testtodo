package com.lookalike.admin.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
//각 사용자의 권한 코드에 항상 ROLE_이 앞에 있어야한다
public enum Role {

    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자");

    private final String key;
    private final String title;

}
