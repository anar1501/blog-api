package com.company.blog.service;

import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto);
}
