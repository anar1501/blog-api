package com.company.blog.service.impl;

import com.company.blog.config.AppConfiguration;
import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.data.entity.Post;
import com.company.blog.data.repository.PostRepository;
import com.company.blog.exception.ResourceNotFoundException;
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

    @Override
    public PostResponseDto getById(Long id) {
        return appConfiguration.modelMapper().map(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)), PostResponseDto.class);
    }

    @Override
    public PostResponseDto updateById(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.getById(id);
        appConfiguration.modelMapper().map(postRequestDto, post);
        return appConfiguration.modelMapper().map(postRepository.save(post), PostResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
       postRepository.delete(postRepository.getById(id));
    }
}
