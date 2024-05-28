package exercise.controller.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> index (@PathVariable int id) {
        var posts = this.posts.stream()
                .filter(p -> p.getUserId() == id)
                .toList();
        return ResponseEntity.ok()
                .body(posts);
    }

    @PostMapping(path="/users/{id}/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> create(@PathVariable int id, @RequestBody Post post) {
        post.setUserId(id);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post);
    }
}
// END
