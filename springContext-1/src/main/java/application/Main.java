package application;

import config.Config;
import org.springframework.boot.SpringApplication;


public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }
}

