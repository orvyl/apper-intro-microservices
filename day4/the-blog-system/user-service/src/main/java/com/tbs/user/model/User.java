package com.tbs.user.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setPreData() {
        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        lastUpdated = now;
        lastLogin = now;
    }

    @PreUpdate
    public void setUpdateTimestamp() {
        lastUpdated = LocalDateTime.now();
    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User() {
    }
}
