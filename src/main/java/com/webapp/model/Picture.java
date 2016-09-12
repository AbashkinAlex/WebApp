package com.webapp.model;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@Column(name = "id", unique = true, nullable = false)
    private Integer id;

    private String path;

    //@ManyToOne//(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")//, nullable = false
    //@ManyToOne()
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}