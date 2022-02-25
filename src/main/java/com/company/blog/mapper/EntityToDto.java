package com.company.blog.mapper;

import com.company.blog.data.dto.response.PostDto;
import com.company.blog.data.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityToDto {
    EntityToDto INSTANCE = Mappers.getMapper(EntityToDto.class);

    PostDto toDto(Post post);
}
