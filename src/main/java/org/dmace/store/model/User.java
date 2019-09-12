package org.dmace.store.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
//@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String email;

    private String city;

    private String password;

    @CreatedDate
    private Date created;

    @OneToMany( mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Post> posts;


    public User() {
        this.posts = new ArrayList<>();
    }

    public User(String name, String email, String city, String password) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Date getCreated() {
        return created;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
