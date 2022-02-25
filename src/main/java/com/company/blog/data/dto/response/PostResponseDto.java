package com.company.blog.data.dto.response;

import lombok.Data;

@Data
public class PostResponseDto {
    private Long id;
    private String content;
    private String title;
    private String description;
}
