package com.blog.study.di;

import org.springframework.stereotype.Component;

@Component
public class SpringBoot implements BackEndFramework {

    @Override
    public String crud() {
        return this.getClass().getSimpleName();
    }
}
