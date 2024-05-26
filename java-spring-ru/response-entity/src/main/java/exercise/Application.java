package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

import static org.springframework.web.servlet.function.ServerResponse.status;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts") // Список страниц
    public ResponseEntity<List<Post>> index(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok()
                        .header("X-Total-Count", String.valueOf(posts.size()))
                                .body(posts.stream().limit(limit).toList());
    }

    @PostMapping("/posts") // Создание страницы
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @GetMapping("/posts/{id}") // Вывод страницы
    public ResponseEntity <Post> show(@PathVariable String id) {

        try {
            var post = posts.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst().orElseThrow();
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var maybePage = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybePage.isPresent()) {
            var page = maybePage.get();
            page.setId(data.getId());
            page.setTitle(data.getTitle());
            page.setBody(data.getBody());
            return ResponseEntity.ok().body(page);
        }
        else {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
    }

    @DeleteMapping("/posts/{id}") // Удаление страницы
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
    // END

}
