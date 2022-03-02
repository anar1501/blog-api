package com.company.blog.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
public class PostResponseDto {
    private Long id;
    private String content;
    private String title;
    private String description;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CommentResponseDto> comments;
}
