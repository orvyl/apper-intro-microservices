package com.tbs.activity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String action;
    @Column(columnDefinition = "TEXT")
    private String data;
    private String identity;

    private LocalDateTime createdAt;

    @PrePersist
    public void setPreData() {
        createdAt = LocalDateTime.now();
    }

    public Activity(String action, String data, String identity) {
        this.action = action;
        this.data = data;
        this.identity = identity;
    }

    public Activity() {
    }
}
