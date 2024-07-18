package application;

import aspect.NotEmpty;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Methods {
    @NotEmpty
    public void noArguments(){

    }

    public void noAnnotation(String string){

    }
    @NotEmpty
    public void withInteger(Integer integer){

    }
    @NotEmpty
    public void withString(String string){

    }
    @NotEmpty
    public void stringCollection(Collection<String> collection){

    }
    @NotEmpty
    public void collectionWithString(Collection<String> collection, String string){

    }
}
