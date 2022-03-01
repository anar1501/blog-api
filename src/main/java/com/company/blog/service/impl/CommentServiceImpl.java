package com.company.blog.service.impl;

import com.company.blog.config.ModelMapperConfiguration;
import com.company.blog.data.dto.request.CommentRequestDto;
import com.company.blog.data.dto.response.CommentResponseDto;
import com.company.blog.data.entity.Comment;
import com.company.blog.data.entity.Post;
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
        Comment comment = ModelMapperConfiguration.map(commentRequestDto, Comment.class);
        comment.setPost(postRepository.getById(postId));
        return ModelMapperConfiguration.map(commentRepository.save(comment), CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> getAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId).stream().map(comment -> ModelMapperConfiguration.map(comment, CommentResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto getByPostAndCommentId(Long postId, Long commentId) throws Exception {
        Post post = postRepository.getById(postId);
        Comment comment = commentRepository.getById(commentId);
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new Exception();
        }
        return ModelMapperConfiguration.map(comment, CommentResponseDto.class);
    }
}
