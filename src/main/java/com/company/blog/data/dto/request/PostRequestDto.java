package com.company.blog.data.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostRequestDto {
    @NotEmpty
    @Size(min = 2, message = "Content should have at least 2 characters")
    private String content;
    @NotEmpty
    @Size(min = 5, message = "Title should have at least 5 characters")
    private String title;
    @NotEmpty
    private String description;
}
