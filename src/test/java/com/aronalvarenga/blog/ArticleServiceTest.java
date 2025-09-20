package com.aronalvarenga.blog;

import com.aronalvarenga.blog.dto.ArticleRequestDto;
import com.aronalvarenga.blog.dto.ArticleResponseDto;
import com.aronalvarenga.blog.exception.ResourceNotFoundException;
import com.aronalvarenga.blog.mapper.ArticleMapper;
import com.aronalvarenga.blog.model.Article;
import com.aronalvarenga.blog.repository.ArticleRepository;
import com.aronalvarenga.blog.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleService articleService;

    private Article article;
    private ArticleRequestDto requestDto;
    private ArticleResponseDto responseDto;

    @BeforeEach
    void setUp() {
        article = Article.builder()
                .id(1L)
                .title("Test Article")
                .content("Test content")
                .author("Test Author")
                .isPublished(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        requestDto = ArticleRequestDto.builder()
                .title("Test Article")
                .content("Test content")
                .author("Test Author")
                .isPublished(true)
                .build();

        responseDto = ArticleResponseDto.builder()
                .id(1L)
                .title("Test Article")
                .content("Test content")
                .author("Test Author")
                .isPublished(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void findById_WhenArticleExists_ShouldReturnArticle() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        when(articleMapper.toResponseDto(article)).thenReturn(responseDto);

        // When
        ArticleResponseDto result = articleService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Article", result.getTitle());
        verify(articleRepository).findById(1L);
        verify(articleMapper).toResponseDto(article);
    }

    @Test
    void findById_WhenArticleDoesNotExist_ShouldThrowException() {
        // Given
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> articleService.findById(1L));
        verify(articleRepository).findById(1L);
        verify(articleMapper, never()).toResponseDto(any());
    }

    @Test
    void create_WhenValidRequest_ShouldCreateArticle() {
        // Given
        when(articleRepository.existsByTitle("Test Article")).thenReturn(false);
        when(articleMapper.toEntity(requestDto)).thenReturn(article);
        when(articleRepository.save(article)).thenReturn(article);
        when(articleMapper.toResponseDto(article)).thenReturn(responseDto);

        // When
        ArticleResponseDto result = articleService.create(requestDto);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(articleRepository).existsByTitle("Test Article");
        verify(articleRepository).save(article);
        verify(articleMapper).toEntity(requestDto);
        verify(articleMapper).toResponseDto(article);
    }

    @Test
    void create_WhenTitleAlreadyExists_ShouldThrowException() {
        // Given
        when(articleRepository.existsByTitle("Test Article")).thenReturn(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> articleService.create(requestDto));
        verify(articleRepository).existsByTitle("Test Article");
        verify(articleRepository, never()).save(any());
    }

    @Test
    void delete_WhenArticleExists_ShouldDeleteArticle() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(true);

        // When
        articleService.delete(1L);

        // Then
        verify(articleRepository).existsById(1L);
        verify(articleRepository).deleteById(1L);
    }

    @Test
    void delete_WhenArticleDoesNotExist_ShouldThrowException() {
        // Given
        when(articleRepository.existsById(1L)).thenReturn(false);

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> articleService.delete(1L));
        verify(articleRepository).existsById(1L);
        verify(articleRepository, never()).deleteById(any());
    }
}
