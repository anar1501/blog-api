package com.company.blog.controller;

import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PaginationInfoPostResponseDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.service.PostService;
import com.company.blog.utils.ConstraintUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto) {
        return new ResponseEntity<>(postService.createPost(postRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginationInfoPostResponseDto> getAll
            (
                    @RequestParam(value = "pageNumber", defaultValue = ConstraintUtil.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
                    @RequestParam(value = "pageSize", defaultValue = ConstraintUtil.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                    @RequestParam(value = "sortBy", defaultValue = ConstraintUtil.DEFAULT_SORT_VALUE, required = false) String sortBy,
                    @RequestParam(value = "sortType",defaultValue = ConstraintUtil.DEFAULT_SORT_TYPE,required = false)String sortType

            )
    {
        return ResponseEntity.ok(postService.getAll(pageNumber, pageSize, sortBy, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updateById(@Valid @PathVariable("id") Long id,@RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.updateById(id, postRequestDto));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return HttpStatus.OK;
    }
}
