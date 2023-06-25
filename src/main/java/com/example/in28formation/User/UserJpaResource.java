package com.example.in28formation.User;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private PosteRepositry posteRepositry;
/////////////////////// Retrieve All Users //////////////////////
    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepositry.findAll();
    }
    //////////////////////// Retrieve User /////////////////////
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepositry.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id-" + id);

        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass())
                        .retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }
    /////////////////////// Delete User //////////////////////
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userRepositry.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        // Update the foreign key references in related entities to null
        List<Post> posts = user.getPosts();
        for (Post post : posts) {
            post.setUser(null);
        }
        userRepositry.deleteById(id);
    }
    //////////////////////// Create User /////////////////////
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user){
        User savedUser = userRepositry.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    //////////////////////// Create Post /////////////////////
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> CreatePost(@Valid @RequestBody Post post, @PathVariable int id) {
        Optional<User> userOptional = userRepositry.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("The following user ID is not found: " + id);
        }
        User user = userOptional.get();
        post.setUser(user); // Associate the post with the user
        Post savedPost = posteRepositry.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{pid}")
                .buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    /////////////////////// Delete Post //////////////////////
    @DeleteMapping (path = "/jpa/users/{id}/posts/{pid}")
    public void DeletePost(@PathVariable int id, @PathVariable int pid) {
        Optional<User> userOptional = userRepositry.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("The following user ID is not found: " + id);
        }

        Optional<Post> postOptional = posteRepositry.findById(pid);
        if (postOptional.isEmpty()) {
            throw new PostNotFoundException("The following post ID is not found: " + pid);
        }
        posteRepositry.deleteById(pid);

    }
    /////////////////////// retrieve All Posts //////////////////////
    @GetMapping("/jpa/users/posts")
    public List<Post> retrieveAllPosts() {
        List<User> users = userRepositry.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users so no posts.");
        }
        List<Post> allPosts = new ArrayList<>();
        for (User user : users) {
            allPosts.addAll(user.getPosts());
        }
        if (allPosts.isEmpty()){
            throw new UserNotFoundException("No posts available yet.");
        }
        return allPosts;
    }
    /////////////////////// Delete All Posts //////////////////////
    @DeleteMapping("/jpa/users/posts")
    public void deleteAllPosts() {
        List<User> users = userRepositry.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users so no posts.");
        }
        List<Post> allPosts = new ArrayList<>();
        for (User user : users) {
            allPosts.addAll(user.getPosts());
        }
        if (allPosts.isEmpty()){
            throw new UserNotFoundException("No posts available yet.");
        }
        posteRepositry.deleteAll();
    }
    /////////////////////// retrieve All Users Posts //////////////////////
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsersPosts(@PathVariable int id){
        Optional<User> userOptional =  userRepositry.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("the following id is not found :" + id);
        }

        return userOptional.get().getPosts();
    }
    ////////////////////// retrieve One Post ///////////////////////
    @GetMapping("/jpa/users/{id}/posts/{pid}")
    public List<Post> retrieveOnePost(@PathVariable int id, @PathVariable int pid) {
        Optional<User> userOptional = userRepositry.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("The following user ID is not found: " + id);
        }

        Optional<Post> postOptional = posteRepositry.findById(pid);
        if (postOptional.isEmpty()) {
            throw new PostNotFoundException("The following post ID is not found: " + pid);
        }
        List<Post> resource = List.of(postOptional.get());
        return resource;
    }


}
