package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        var service = new Address();
        for (
                Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                try {
                    method.invoke(service);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Method: "
                        + method.getName()
                        + " returns a value of type "
                        + method.getReturnType().getTypeName().replace("java.lang.",""));
            }
        }
        // END
    }
}
