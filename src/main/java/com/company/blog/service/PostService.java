package com.company.blog.service;

import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;
import com.company.blog.data.dto.response.PaginationInfoPostResponseDto;
import com.company.blog.data.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {

    PaginationInfoPostResponseDto getAll(Integer pageNumber, Integer pageSize,String sortBy,String sortType);

    PostResponseDto createPost(PostRequestDto requestDto);

    PostResponseDto getById(Long id);

    PostResponseDto updateById(Long id, PostRequestDto postRequestDto);

    void deleteById(Long id);

}
