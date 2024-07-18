package tests;

import application.Methods;
import config.ProjectConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProjectConfig.class})
public class AspectTest {

    @Autowired
    private Methods methods;

    @Test
    public void testNoArgument(){
        assertDoesNotThrow(() -> methods.noArguments());
    }
    @Test
    public void testNoAnnotation(){
        assertDoesNotThrow(() -> methods.noAnnotation(null));
    }
    @Test
    public void testWithInteger(){
        assertDoesNotThrow(() -> methods.withInteger(1));
    }
    @Test
    public void testWithString(){
        assertDoesNotThrow(() -> methods.withString("arcane pudge"));
    }
    @Test
    public void testStringCollection(){
        assertDoesNotThrow(() -> methods.stringCollection(List.of("arcane pudge")));
    }
    @Test
    public void testCollectionWithString(){
        assertDoesNotThrow(() -> methods.collectionWithString(List.of("arcane pudge"), "arcane juggernaut"));
    }
    @Test
    public void testAllThrow() {
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.withString(null));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.withString(""));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.stringCollection(null));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.stringCollection(List.of()));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.collectionWithString(List.of(), null));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.collectionWithString(List.of(), "Hello Spring!"));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.collectionWithString(List.of("Hello Spring!"), null));
        assertThrowsExactly(IllegalArgumentException.class, () -> methods.collectionWithString(List.of("Hello Spring!"), ""));
    }
}
