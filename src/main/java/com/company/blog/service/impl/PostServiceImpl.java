package com.company.blog.service.impl;

import com.company.blog.config.AppConfiguration;
import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.data.entity.Post;
import com.company.blog.data.repository.PostRepository;
import com.company.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.blog.mapper.EntityToDto.INSTANCE;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AppConfiguration appConfiguration;

    @Override
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post savePost = postRepository.save(appConfiguration.modelMapper().map(requestDto, Post.class));
        return INSTANCE.toDto(savePost);
    }

    @Override
    public List<PostResponseDto> findAll() {
        return postRepository.findAll().stream().map(post -> appConfiguration.modelMapper().map(post, PostResponseDto.class)).collect(Collectors.toList());
    }
}
