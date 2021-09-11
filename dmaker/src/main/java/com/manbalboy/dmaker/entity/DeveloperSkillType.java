package com.manbalboy.dmaker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeveloperSkillType {
    BACK_END("백엔드 개발자"),
    FRONT_END("프론트엔드 개발자"),
    FULl_STACK("풀스택 개발자");

    private final String discription;
}
