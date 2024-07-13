package testAspect;

import methods.Methods;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
@ContextConfiguration(classes = {Methods.class})
@ExtendWith(SpringExtension.class)
public class aspectTest {

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
    public void testAllThrow(){
        assertDoesNotThrow(() -> methods.withString(null));
        assertDoesNotThrow(() -> methods.withString(""));
        assertDoesNotThrow(() -> methods.stringCollection(null));
        assertDoesNotThrow(() -> methods.stringCollection(List.of()));
        assertDoesNotThrow(() -> methods.collectionWithString(List.of(), null));
        assertDoesNotThrow(() -> methods.collectionWithString(List.of(), "arcane juggernaut"));
        assertDoesNotThrow(() -> methods.collectionWithString(List.of("arcane pudge"), null));
        assertDoesNotThrow(() -> methods.collectionWithString(List.of("arcane pudge"), ""));
    }
}
