package com.blog.study.di;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Developer {

    private final BackEndFramework backEndFramework;

    public String develop() {
        return backEndFramework.crud();
    }
}
