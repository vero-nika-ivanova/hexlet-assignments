package exercise;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

import static exercise.Data.getUsers;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = getUsers();

    // BEGIN

    @Autowired
    private UserProperties adminEmail;

    @GetMapping("/admins")
    public List<String> admins() {
        var adminEmails = adminEmail.getAdmins();
        return users.stream()
                .filter(user -> adminEmails.contains(user.getEmail()))
                .map(User::getName)
                .toList();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
