package tests;

import exercise.Cat;
import exercise.Dog;
import exercise.Parrot;
import exercise.Person;
import config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class})
public class ContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testCatComponentWasAdded() {
        Cat cat = context.getBean(Cat.class);

        assertEquals("Zuma", cat.getName());
    }

    @Test
    public void testDogComponentWasAdded() {
        Dog dog = context.getBean(Dog.class);

        assertEquals("Essa", dog.getName());
    }

    @Test
    public void testParrot1ComponentWasAdded() {
        Parrot parrot = context.getBean("Vasiy", Parrot.class);

        assertEquals("Vasiy", parrot.getName());
    }

    @Test
    public void testParrot2ComponentWasAdded() {
        Parrot parrot = context.getBean("Petiy", Parrot.class);

        assertEquals("Petiy", parrot.getName());
    }

    @Test
    public void testPersonComponentWasAdded() {
        Person person = context.getBean(Person.class);

        assertEquals("Zuma", person.getCat().getName());
        assertEquals("Essa", person.getDog().getName());
        assertEquals("Vasiy", person.getParrot1().getName());
        assertEquals("Petiy", person.getParrot2().getName());
        assertEquals("Maks", person.getName());
    }
}