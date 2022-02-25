package com.company.blog.data.dto.request;

import lombok.Data;

@Data
public class PostRequestDto {
    private String content;
    private String title;
    private String description;
}
