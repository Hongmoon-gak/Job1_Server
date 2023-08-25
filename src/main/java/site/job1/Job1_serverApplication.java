package site.job1;

import site.job1.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class Job1_serverApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job1_serverApplication.class, args);
    }

}
