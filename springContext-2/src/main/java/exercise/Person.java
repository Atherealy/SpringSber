package exercise;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Person {

    @Autowired
    private Cat cat;

    @Autowired
    private Dog dog;
    @Autowired
    @Qualifier("Vasiy")
    private Parrot parrot1;

    @Autowired
    @Qualifier("Petiy")
    private Parrot parrot2;

    private String name = "Maks";
}
