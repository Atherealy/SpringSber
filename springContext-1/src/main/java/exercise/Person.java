package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Person {
    private String name;
    private Parrot parrot_1;
    private Parrot parrot_2;
    private Cat cat;
    private Dog dog;
}
