package com.springbootlearning.learningspringboot3.ch2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class VideoEntity {
    private @Id @GeneratedValue Long id;
    private String username;
    private String name;
    private String description;

    protected VideoEntity() {
        this(null, null, null);
    }

    VideoEntity(String username, String name, String description) {
        this.id = null;
        this.username = username;
        this.description = description;
        this.name = name;
    }
}
