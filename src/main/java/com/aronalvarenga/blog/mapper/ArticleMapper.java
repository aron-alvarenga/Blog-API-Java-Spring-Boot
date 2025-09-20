package com.aronalvarenga.blog.mapper;

import com.aronalvarenga.blog.dto.ArticleRequestDto;
import com.aronalvarenga.blog.dto.ArticleResponseDto;
import com.aronalvarenga.blog.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Article toEntity(ArticleRequestDto dto);

    ArticleResponseDto toResponseDto(Article entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(ArticleRequestDto dto, @MappingTarget Article entity);
}
