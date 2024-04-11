package com.peli.demo.restaurant.core.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "n1u-user-core")
public class RestaurantCoreProperties {
    private final HttpProperties http = new HttpProperties();
}
