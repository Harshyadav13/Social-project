package com.social.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;

    @OneToMany
    private List<User> liked = new ArrayList<>();

    private LocalDateTime createdAt;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Post() {

    }

    public Post(Integer id, String caption, String image, String video, User user, List<User> liked, LocalDateTime createdAt, List<Comment> comments) {
      super();
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.user = user;
        this.liked = liked;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Getter and setter for 'id'
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and setter for 'caption'
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    // Getter and setter for 'image'
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Getter and setter for 'video'
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    // Getter and setter for 'user'
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and setter for 'liked'
    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    // Getter and setter for 'createdAt'
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
