package com.example.in28formation.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@ApiModel(description = "All details about the user.")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2)
    private String name;
    @Past
    private Date BirthDate;
    @OneToMany(mappedBy = "user")
    private List<Post>  posts;
    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        BirthDate = birthDate;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", BirthDate=" + BirthDate +
                '}';
    }
}
