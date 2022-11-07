package com.blog.study.enumtype.spring_container;

import com.blog.study.enumtype.spring_container.game_start.GameStart;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Game {

    KART_RIDER("kart", context -> context.getBean("kartRider", GameStart.class).start()),
    MAPLE_STORY("maple", context -> context.getBean("mapleStory", GameStart.class).start()),
    LEAGUE_OF_LEGEND("lol", context -> context.getBean("lol", GameStart.class).start());

    private String name;

    private Client client;

    private static Map<String, Game> games = Arrays.stream(values())
            .collect(Collectors.toMap(g -> g.name, Function.identity()));

    private static Game getGame(String gameName) {
        String key = games.keySet().stream().filter(g -> g.contains(gameName)).findFirst()
                .orElseGet(() -> MAPLE_STORY.name);

        return games.get(key);
    }

    public static void start(String gameName, ApplicationContext ac) {
        Game game = getGame(gameName);
        game.client.execute(ac);
    }
}
