package com.company.blog.service;

import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto createPost(PostRequestDto requestDto);

    List<PostResponseDto> findAll();

    PostResponseDto getById(Long id);

    PostResponseDto updateById(Long id, PostRequestDto postRequestDto);

    void deleteById(Long id);
}
