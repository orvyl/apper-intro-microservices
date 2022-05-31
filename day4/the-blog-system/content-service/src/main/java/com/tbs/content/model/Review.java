package com.tbs.content.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contentId", nullable = false)
    private Content content;

    private String userId;
    private String comment;
    private Integer stars;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setPreData() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdated = now;
    }

    @PreUpdate
    public void setUpdatedTimestamp() {
        lastUpdated = LocalDateTime.now();
    }

    public Review(Content content, String userId, String comment, Integer stars) {
        this.content = content;
        this.userId = userId;
        this.comment = comment;
        this.stars = stars;
    }

    public Review() {
    }
}
