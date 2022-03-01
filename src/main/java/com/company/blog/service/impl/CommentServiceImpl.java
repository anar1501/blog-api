package com.company.blog.service.impl;

import com.company.blog.config.AppConfiguration;
import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;
import com.company.blog.data.entity.Comment;
import com.company.blog.data.entity.Post;
import com.company.blog.data.repository.CommentRepository;
import com.company.blog.data.repository.PostRepository;
import com.company.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final AppConfiguration appConfiguration;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto) {
        Comment comment = appConfiguration.modelMapper().map(commentRequestDto, Comment.class);
        comment.setPost(postRepository.getById(postId));
        return appConfiguration.modelMapper().map(commentRepository.save(comment), CommentResponseDto.class);
    }
}
