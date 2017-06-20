package com.bat.desk.web.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true)
public class GitConfiguration {

    @Bean
    GitRepositoryState gitRepositoryInformation() {
        return new GitRepositoryState();
    }

    public class GitRepositoryState {

        @JsonProperty
        @Value("${git.branch}")
        String branch;
        @JsonProperty
        @Value("${git.tags}")
        String tags;
        @JsonProperty
        @Value("${git.commit.id}")
        String commitId;
        @JsonProperty
        @Value("${git.build.time}")
        String buildTime;
        @JsonProperty
        @Value("${git.commit.user.name}")
        String commitUserName;
        @JsonProperty
        @Value("${git.commit.message.full}")
        String commitMessageFull;
        @JsonProperty
        @Value("${git.commit.time}")
        String commitTime;
        @JsonProperty
        @Value("${git.commit.id.describe}")
        String commitDescription;

        public GitRepositoryState() {
        }
    }
}
