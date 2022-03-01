package com.company.blog.data.dto.request;

import lombok.Data;

@Data
public class CommentRequestDto {
    private String name;
    private String email;
    private String body;
}
