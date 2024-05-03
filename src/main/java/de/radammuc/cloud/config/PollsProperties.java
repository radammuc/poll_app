package de.radammuc.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConfigurationProperties
@ConstructorBinding // not required for Spring Boot 2.6
public record PollsProperties(List<PollProps> polls) {

    public record PollProps(String name, String description, List<String> options, int maxSelections) {
    }

}

