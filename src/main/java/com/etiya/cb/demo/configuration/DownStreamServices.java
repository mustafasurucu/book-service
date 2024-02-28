package com.etiya.cb.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "down-stream-services")
public record DownStreamServices(
        String bookNameServiceUrl,
        String bookAuthorServiceUrl
) {
}
