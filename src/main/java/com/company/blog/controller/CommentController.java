package com.company.blog.controller;

import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;
import com.company.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId, CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getAllByPostId(postId));
    }
}
