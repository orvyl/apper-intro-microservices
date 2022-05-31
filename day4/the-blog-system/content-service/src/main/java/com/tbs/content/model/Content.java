package com.tbs.content.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToMany(mappedBy = "content", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Review> reviews;

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

    public Content(String userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Content() {
    }
}
