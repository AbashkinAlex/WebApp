package com.webapp.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.*;

@Entity
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @NotEmpty
    @Column(name = "STATE", nullable = false)
    private String state = State.ACTIVE.getState();

    @NotEmpty
    @Column(name = "MESSAGE", nullable = true)
    @Length(max = 500)
    private String message;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//, mappedBy = "user"
    @JoinTable(name = "user_pictures",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "picture_id")})
    private List<Picture> userPictures = new ArrayList<Picture>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserPictures(List<Picture> userPictures) {this.userPictures = userPictures;}

    public List<Picture> getUserPictures() {return userPictures;}

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", state='" + state + '\'' +
                ", userProfiles=" + userProfiles +
                '}';
    }
}
