package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = {"application", "aspect", "annotation"})
@EnableAspectJAutoProxy
public class ProjectConfig {
    public static void main(String[] args) {
        SpringApplication.run(ProjectConfig.class, args);
    }
}