package com.company.blog.service.impl;

import com.company.blog.config.ModelMapperConfiguration;
import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;
import com.company.blog.data.entity.Comment;
import com.company.blog.data.repository.CommentRepository;
import com.company.blog.data.repository.PostRepository;
import com.company.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapperConfiguration modelMapperConfiguration;

    @Override
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto) {
        Comment comment = modelMapperConfiguration.map(commentRequestDto, Comment.class);
        comment.setPost(postRepository.getById(postId));
        return modelMapperConfiguration.map(commentRepository.save(comment), CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> getAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId).stream().map(comment -> modelMapperConfiguration.map(comment, CommentResponseDto.class)).collect(Collectors.toList());
    }
}
