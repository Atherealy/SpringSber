package config;

import exercise.Cat;
import exercise.Dog;
import exercise.Parrot;
import exercise.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Config {
    @Bean
    public Cat getCat(){
        return new Cat("Zuma");
    }

    @Bean
    public Dog getDog(){
        return new Dog("Essa");
    }

    @Bean(name = "Vasiy")
    public Parrot getParrot1(){
        return new Parrot("Vasiy");
    }

    @Bean(name = "Petiy")
    public Parrot getParrot2(){
        return new Parrot("Petiy");
    }

    @Bean
    public Person getPerson(Cat cat, Dog dog, @Qualifier("Vasiy") Parrot parrot1, @Qualifier("Petiy") Parrot parrot2){
        return new Person("Maks", parrot1, parrot2, cat, dog);
    }
}
