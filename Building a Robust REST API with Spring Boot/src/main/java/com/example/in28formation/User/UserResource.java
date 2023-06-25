package com.example.in28formation.User;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
private UserDqoService service;
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.FindAll();
    }
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.finduser(id);

        if (user == null)
            throw new UserNotFoundException("id-" + id);

        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo =
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass())
                        .retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping (path = "/users/{id}")
    public void DeleteUser(@PathVariable int id) {
        User user = service.deleteuser(id);
        if (user == null)
            throw new UserNotFoundException("id-"+ id);
    }
    @PostMapping("/users")
    public ResponseEntity<Object> creatuser(@Valid @RequestBody User user){
        User savedUser = service.saveuser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
