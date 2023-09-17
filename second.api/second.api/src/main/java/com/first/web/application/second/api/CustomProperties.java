package com.first.web.application.second.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.first.web.application.second.api")
public class CustomProperties {
	private String apiUrl;
}
