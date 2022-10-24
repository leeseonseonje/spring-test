package com.blog.study.github_api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

class GitHubCommitApiTest {

    @Test
    void gitHub_commit_search() throws IOException {
        GitHubCommitApi sut = new GitHubCommitApi();

        GitHub github = new GitHubBuilder().withOAuthToken("").build();
        github.checkApiUrlValidity();

        for (int i = 0; i < 100; i++) {
            github.searchCommits()
                    .author("leeseonseonje")
                    .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE).list().withPageSize(1)
                    ._iterator(1).next().getCommitDate();
        }
        LocalDate seonjeLastCommit = sut.getLastCommitDate("leeseonseonje");
        LocalDate taeyeongLastCommit = sut.getLastCommitDate("xodud001");
        LocalDate yeongdongeLastCommit = sut.getLastCommitDate("yeongdonge");

        System.out.println("선제 마지막 커밋: " + seonjeLastCommit);
        System.out.println("태영 마지막 커밋: " + taeyeongLastCommit);
        System.out.println("동영 마지막 커밋: " + yeongdongeLastCommit);
    }

    @Test
    @DisplayName("깃허브 API 속도 최적화")
    void test() {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
    }
}