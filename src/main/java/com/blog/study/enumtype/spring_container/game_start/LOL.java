package com.blog.study.enumtype.spring_container.game_start;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LOL implements GameStart {

    @Override
    public void start() {
        log.info("lol start");
    }
}
