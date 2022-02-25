package com.company.blog.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
}
