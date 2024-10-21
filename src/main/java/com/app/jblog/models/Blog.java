package com.app.jblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "image")
    private String image;

    @Column(nullable = false, name = "major")
    private String field;

    @Column(nullable = false, name = "dataTime")
    private Date dateTime;

    public Blog(String name, String description, String image, String field) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.field = field;
        this.dateTime = new Date();
    }
}
