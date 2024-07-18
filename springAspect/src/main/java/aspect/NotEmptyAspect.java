package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Aspect
@Component
public class NotEmptyAspect {
    @Before("@annotation(NotEmpty)")
    public void checkArguments(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();

        if (arguments.length == 0){
            return;
        }
        for (Object argument : arguments){
            if (argument == null) {
                throw new IllegalArgumentException("Argument must not be null");
            }

            if (argsString(argument)){
                checkString((String) argument);
            } else if (argCollection(argument)){
                checkCollection((Collection<?>) argument);
            }
        }
    }
        private boolean argsString(Object object) {
            return object instanceof String;
        }

        private void checkString(String string) {
            if (string.isEmpty()) {
                throw new IllegalArgumentException("Provided string must not be empty");
            }
        }

        private boolean argCollection(Object object) {
            return object instanceof Collection<?>;
        }

        private void checkCollection(Collection<?> collection) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Provided collection must not be empty");
            }
        }


    }
