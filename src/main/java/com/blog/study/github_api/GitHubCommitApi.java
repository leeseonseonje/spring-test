package com.blog.study.github_api;

import org.kohsuke.github.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

public class GitHubCommitApi {

    private GitHub github;
    private String token = "";

    public LocalDate getLastCommitDate(String userId) {
        try {
            connectToGithub(token);
            return commitToLocalDate(createCommitSearch(userId));
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to connect gitHub");
        }

    }

    private PagedSearchIterable<GHCommit> createCommitSearch(String userId) {
        return github.searchCommits()
                .author(userId)
                .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE).list().withPageSize(1);
    }

    private void connectToGithub(String token) throws IOException {
        github = new GitHubBuilder().withOAuthToken(token).build();
        github.checkApiUrlValidity();
    }

    private LocalDate commitToLocalDate(PagedSearchIterable<GHCommit> commits) throws IOException {
        return commits._iterator(1).next().getCommitDate()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
