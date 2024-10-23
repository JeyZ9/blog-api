package com.app.jblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
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

    @Column(nullable = false, name = "field")
    private String field;

    @Column(nullable = false, name = "dateTime")
    private String dateTime;

//    public Blog(String name, String description, String image, String field) {
//        this.name = name;
//        this.description = description;
//        this.image = image;
//        this.field = field;
//    }
}
