package com.peli.demo.restaurant.core;

import com.peli.demo.restaurant.core.util.DefaultProfileUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Restaurant-core service", version = "v1"))
public class RestaurantCoreApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestaurantCoreApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store"))
              .map(key -> "https")
              .orElse("http");
        String serverPort = env.getProperty("server.port");
        String contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path"))
              .orElse("/");
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info(
              "\n----------------------------------------------------------\n\t"
                    + "Application '{}' is running! Access URLs:\n\t"
                    + "Local: \t\t{}://localhost:{}{}\n\t"
                    + "External: \t{}://{}:{}{}\n\t"
                    + "Profile(s): \t{}\n----------------------------------------------------------",
              env.getProperty("spring.application.name"),
              protocol,
              serverPort,
              contextPath,
              protocol,
              hostAddress,
              serverPort,
              contextPath,
              env.getActiveProfiles()
        );
    }
}
