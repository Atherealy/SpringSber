package methods;

import annotation.NotEmpty;

import java.util.Collection;

public class Methods {
    @NotEmpty
    public void noArguments(){

    }
    @NotEmpty
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
