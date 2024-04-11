package com.peli.demo.restaurant.core.config.logging;

import static org.zalando.logbook.core.Conditions.exclude;
import static org.zalando.logbook.core.Conditions.requestTo;

import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

public class LogbookConfiguration {

    public static Logbook serverLogger() {
        return logbook(new ServerHttpLogger());
    }

    public static LogbookClientHttpRequestInterceptor clientLoggerInterceptor() {
        return new LogbookClientHttpRequestInterceptor(logbook(new ClientHttpLogger()));
    }

    public static Logbook logbook(HttpLogWriter writer) {
        return Logbook.builder()
              .sink(new DefaultSink(new CustomHttpLogger(), writer))
              .condition(exclude(
                    requestTo("/actuator/**"),
                    requestTo("/healthz")))
              .build();
    }
}
