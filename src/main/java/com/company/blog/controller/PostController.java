package com.company.blog.controller;

import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        return new ResponseEntity<>(postService.createPost(postRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updateById(@PathVariable("id") Long id, PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.updateById(id, postRequestDto));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return HttpStatus.OK;
    }
}
