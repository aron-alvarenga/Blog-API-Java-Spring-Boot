package com.aronalvarenga.blog.service;

import com.aronalvarenga.blog.dto.ArticleRequestDto;
import com.aronalvarenga.blog.dto.ArticleResponseDto;
import com.aronalvarenga.blog.exception.ResourceNotFoundException;
import com.aronalvarenga.blog.mapper.ArticleMapper;
import com.aronalvarenga.blog.model.Article;
import com.aronalvarenga.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    /**
     * Busca todos os artigos
     */
    public List<ArticleResponseDto> findAll() {
        log.info("Buscando todos os artigos");
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::toResponseDto)
                .toList();
    }

    /**
     * Busca todos os artigos com paginação
     */
    public Page<ArticleResponseDto> findAll(Pageable pageable) {
        log.info("Buscando artigos com paginação: {}", pageable);
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(articleMapper::toResponseDto);
    }

    /**
     * Busca artigos publicados
     */
    public List<ArticleResponseDto> findPublishedArticles() {
        log.info("Buscando artigos publicados");
        List<Article> articles = articleRepository.findByIsPublishedTrue();
        return articles.stream()
                .map(articleMapper::toResponseDto)
                .toList();
    }

    /**
     * Busca artigos publicados com paginação
     */
    public Page<ArticleResponseDto> findPublishedArticles(Pageable pageable) {
        log.info("Buscando artigos publicados com paginação: {}", pageable);
        Page<Article> articles = articleRepository.findByIsPublishedTrue(pageable);
        return articles.map(articleMapper::toResponseDto);
    }

    /**
     * Busca artigo por ID
     */
    public ArticleResponseDto findById(Long id) {
        log.info("Buscando artigo com ID: {}", id);
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artigo não encontrado com ID: " + id));
        return articleMapper.toResponseDto(article);
    }

    /**
     * Busca artigos por título
     */
    public List<ArticleResponseDto> findByTitle(String title) {
        log.info("Buscando artigos por título: {}", title);
        List<Article> articles = articleRepository.findByTitleContainingIgnoreCase(title);
        return articles.stream()
                .map(articleMapper::toResponseDto)
                .toList();
    }

    /**
     * Busca artigos publicados por título
     */
    public List<ArticleResponseDto> findPublishedByTitle(String title) {
        log.info("Buscando artigos publicados por título: {}", title);
        List<Article> articles = articleRepository.findPublishedByTitleContainingIgnoreCase(title);
        return articles.stream()
                .map(articleMapper::toResponseDto)
                .toList();
    }

    /**
     * Busca artigos por autor
     */
    public List<ArticleResponseDto> findByAuthor(String author) {
        log.info("Buscando artigos por autor: {}", author);
        List<Article> articles = articleRepository.findByAuthor(author);
        return articles.stream()
                .map(articleMapper::toResponseDto)
                .toList();
    }

    /**
     * Cria um novo artigo
     */
    @Transactional
    public ArticleResponseDto create(ArticleRequestDto requestDto) {
        log.info("Criando novo artigo: {}", requestDto.getTitle());
        
        // Verifica se já existe um artigo com o mesmo título
        if (articleRepository.existsByTitle(requestDto.getTitle())) {
            throw new IllegalArgumentException("Já existe um artigo com o título: " + requestDto.getTitle());
        }

        Article article = articleMapper.toEntity(requestDto);
        article = articleRepository.save(article);
        
        log.info("Artigo criado com sucesso. ID: {}", article.getId());
        return articleMapper.toResponseDto(article);
    }

    /**
     * Atualiza um artigo existente
     */
    @Transactional
    public ArticleResponseDto update(Long id, ArticleRequestDto requestDto) {
        log.info("Atualizando artigo com ID: {}", id);
        
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artigo não encontrado com ID: " + id));

        // Verifica se o novo título já existe em outro artigo
        if (!existingArticle.getTitle().equals(requestDto.getTitle()) && 
            articleRepository.existsByTitle(requestDto.getTitle())) {
            throw new IllegalArgumentException("Já existe um artigo com o título: " + requestDto.getTitle());
        }

        articleMapper.updateEntityFromDto(requestDto, existingArticle);
        Article updatedArticle = articleRepository.save(existingArticle);
        
        log.info("Artigo atualizado com sucesso. ID: {}", updatedArticle.getId());
        return articleMapper.toResponseDto(updatedArticle);
    }

    /**
     * Exclui um artigo
     */
    @Transactional
    public void delete(Long id) {
        log.info("Excluindo artigo com ID: {}", id);
        
        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artigo não encontrado com ID: " + id);
        }
        
        articleRepository.deleteById(id);
        log.info("Artigo excluído com sucesso. ID: {}", id);
    }

    /**
     * Publica um artigo
     */
    @Transactional
    public ArticleResponseDto publish(Long id) {
        log.info("Publicando artigo com ID: {}", id);
        
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artigo não encontrado com ID: " + id));
        
        article.setIsPublished(true);
        Article publishedArticle = articleRepository.save(article);
        
        log.info("Artigo publicado com sucesso. ID: {}", publishedArticle.getId());
        return articleMapper.toResponseDto(publishedArticle);
    }

    /**
     * Despublica um artigo
     */
    @Transactional
    public ArticleResponseDto unpublish(Long id) {
        log.info("Despublicando artigo com ID: {}", id);
        
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artigo não encontrado com ID: " + id));
        
        article.setIsPublished(false);
        Article unpublishedArticle = articleRepository.save(article);
        
        log.info("Artigo despublicado com sucesso. ID: {}", unpublishedArticle.getId());
        return articleMapper.toResponseDto(unpublishedArticle);
    }
}
