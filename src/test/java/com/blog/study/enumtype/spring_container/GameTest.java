package com.blog.study.enumtype.spring_container;

import com.blog.study.enumtype.spring_container.game_start.GameStart;
import com.blog.study.enumtype.spring_container.game_start.KartRider;
import com.blog.study.enumtype.spring_container.game_start.LOL;
import com.blog.study.enumtype.spring_container.game_start.MapleStory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class GameTest {

    @TestConfiguration
    static class GameBean {

        @Bean
        public GameStart kartRider() {
            return new KartRider();
        }

        @Bean
        public MapleStory mapleStory() {
            return new MapleStory();
        }

        @Bean
        public GameStart lol() {
            return new LOL();
        }
    }

    ApplicationContext ac = new AnnotationConfigApplicationContext(GameBean.class);

    @Test
    void enum_test() {
        Game.start("kart", ac);
        Game.start("maple", ac);
        Game.start("lol", ac);
    }
}
