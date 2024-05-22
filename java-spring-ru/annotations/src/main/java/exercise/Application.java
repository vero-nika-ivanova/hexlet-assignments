package exercise;

import exercise.annotation.Inspect;
import exercise.model.Address;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

// BEGIN
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);
        // BEGIN
        for (
                Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                try {
                    method.invoke(address);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Method "
                        + method.getName()
                        + " returns a value of type "
                        + method.getReturnType().getTypeName().replace("java.lang.",""));
            }
        }

        // END
    }
}
