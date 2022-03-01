package com.company.blog.controller;

import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;
import com.company.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long posId, CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComment(posId, commentRequestDto), HttpStatus.CREATED);
    }
}
