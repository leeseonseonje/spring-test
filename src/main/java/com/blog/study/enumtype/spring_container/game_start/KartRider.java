package com.blog.study.enumtype.spring_container.game_start;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KartRider implements GameStart {

    @Override
    public void start() {
        log.info("kartRider start");
    }
}
