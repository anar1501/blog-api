package com.company.blog.data.dto.request;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long id;
    private String name;
    private String email;
    private String body;
}
