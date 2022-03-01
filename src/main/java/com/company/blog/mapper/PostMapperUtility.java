package com.company.blog.mapper;

import com.company.blog.config.AppConfiguration;
import com.company.blog.data.dto.response.PaginationInfoPostResponseDto;
import com.company.blog.data.dto.response.PostResponseDto;
import com.company.blog.data.entity.Post;
import com.company.blog.data.repository.PostRepository;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class PostMapperUtility {
    private final AppConfiguration appConfiguration;

    public PaginationInfoPostResponseDto preparePaginationInfoPostResponseDto(List<Post> postList, Page<Post> postPage) {
        PaginationInfoPostResponseDto returnValue = new PaginationInfoPostResponseDto();
        returnValue.setContent(postList.stream().map(post -> appConfiguration.modelMapper().map(post, PostResponseDto.class)).collect(Collectors.toList()));
        returnValue.setPageNumber(postPage.getNumber());
        returnValue.setPageSize(postPage.getSize());
        returnValue.setTotalElements(postPage.getTotalElements());
        returnValue.setTotalPages(postPage.getTotalPages());
        returnValue.setLast(postPage.isLast());
        return returnValue;
    }
}
