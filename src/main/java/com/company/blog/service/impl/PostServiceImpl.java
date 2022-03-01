package com.company.blog.service.impl;

import com.company.blog.config.AppConfiguration;
import com.company.blog.data.dto.request.PostRequestDto;
import com.company.blog.data.dto.response.PaginationInfoPostResponseDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.data.entity.Post;
import com.company.blog.data.repository.PostRepository;
import com.company.blog.exception.ResourceNotFoundException;
import com.company.blog.mapper.PostMapperUtility;
import com.company.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.company.blog.mapper.EntityToDto.INSTANCE;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AppConfiguration appConfiguration;
    private final PostMapperUtility postMapperUtility;

    @Override
    public PaginationInfoPostResponseDto getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        Sort sort = sortType.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postRepositoryAll = postRepository.findAll(pageable);
        List<Post> postList = postRepositoryAll.getContent();
        return postMapperUtility.preparePaginationInfoPostResponseDto(postList,postRepositoryAll);
    }

    @Override
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post savePost = postRepository.save(appConfiguration.modelMapper().map(requestDto, Post.class));
        return INSTANCE.toDto(savePost);
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
