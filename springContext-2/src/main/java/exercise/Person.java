package exercise;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class Person {
    private String name = "Maks";
    private Parrot parrot_1;
    private Parrot parrot_2;
    private Cat cat;
    private Dog dog;
}
