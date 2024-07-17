package config;

import exercise.Parrot;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "exercise")
public class Config {
    @Bean(name = "Vasiy")
    public Parrot getParrot1(){
        return new Parrot("Vasiy");
    }

    @Bean(name = "Petiy")
    public Parrot getParrot2(){
        return new Parrot("Petiy");
    }

}
