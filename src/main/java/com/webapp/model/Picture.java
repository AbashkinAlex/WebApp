package com.webapp.model;

import javax.persistence.*;

//@Entity(name = "picture")
public class Picture {

    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String path;

    //@ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}