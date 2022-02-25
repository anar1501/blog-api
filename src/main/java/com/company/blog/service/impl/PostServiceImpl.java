package com.company.blog.service.impl;

import com.company.blog.config.AppConfiguration;
import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.data.entity.Post;
import com.company.blog.data.repository.PostRepository;
import com.company.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AppConfiguration appConfiguration;

    @Override
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post savePost = postRepository.save(appConfiguration.modelMapper().map(requestDto, Post.class));
        return appConfiguration.modelMapper().map(savePost, PostResponseDto.class);
    }
}
