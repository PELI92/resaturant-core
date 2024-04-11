package com.peli.demo.restaurant.core.config;

import com.peli.demo.restaurant.core.config.logging.LogbookConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;

@Configuration
public class ServerHttpLoggerConfiguration {

    @Bean
    public Logbook logbook() {
        return LogbookConfiguration.serverLogger();
    }
}
