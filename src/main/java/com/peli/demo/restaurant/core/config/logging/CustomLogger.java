package com.peli.demo.restaurant.core.config.logging;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.StructuredHttpLogFormatter;

public class CustomLogger implements StructuredHttpLogFormatter {

    @Override
    public String format(Map<String, Object> content) {
        return content.entrySet().stream()
              .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
              .collect(Collectors.joining(" "));
    }

    @Override
    public Map<String, Object> prepare(Precorrelation precorrelation, HttpRequest request) throws IOException {

        final Map<String, Object> content = new LinkedHashMap<>();
        content.put("type", "request");
        content.put("method", request.getMethod());
        content.put("path", request.getPath() + (request.getQuery().isBlank() ? " " : "/" + request.getQuery()));
        prepareBody(request).ifPresent(body -> content.put("body", body));
        prepareHeaders(request).ifPresent(headers -> content.put("headers", headers));
        return content;
    }

    @Override
    public Map<String, Object> prepare(Correlation correlation, HttpResponse response) throws IOException {
        final Map<String, Object> content = new LinkedHashMap<>();
        content.put("type", "response");
        content.put("duration-ms", correlation.getDuration().toMillis());
        content.put("status", response.getStatus());
        prepareBody(response).ifPresent(body -> content.put("body", body));
        prepareHeaders(response).ifPresent(headers -> content.put("headers", headers));
        return content;
    }
}
