package com.blog.study.di;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringBoot implements BackEndFramework {

    @Override
    public void crud() {
        log.info("BackEndFramework: {}", this.getClass().getSimpleName());
    }
}
