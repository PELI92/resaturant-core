package com.peli.demo.restaurant.core.config.logging;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

@Slf4j
public class ServerHttpLogger implements HttpLogWriter {
    @Override
    public boolean isActive() {
        return log.isInfoEnabled();
    }

    @Override
    public void write(@NonNull Precorrelation precorrelation, @NonNull String request) {
        log.info(request);
    }

    @Override
    public void write(@NonNull Correlation correlation, @NonNull String response) {
        log.info(response);
    }
}
