package com.company.blog.data.dto.response;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String content;
    private String title;
    private String description;
}
