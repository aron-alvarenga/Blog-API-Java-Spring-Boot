package com.aronalvarenga.blog.controller;

import com.aronalvarenga.blog.dto.ArticleRequestDto;
import com.aronalvarenga.blog.dto.ArticleResponseDto;
import com.aronalvarenga.blog.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * Busca todos os artigos
     */
    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        log.info("GET /api/v1/articles - Buscando todos os artigos");
        List<ArticleResponseDto> articles = articleService.findAll();
        return ResponseEntity.ok(articles);
    }

    /**
     * Busca todos os artigos com paginação
     */
    @GetMapping("/paginated")
    public ResponseEntity<Page<ArticleResponseDto>> getAllArticlesPaginated(
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("GET /api/v1/articles/paginated - Buscando artigos com paginação");
        Page<ArticleResponseDto> articles = articleService.findAll(pageable);
        return ResponseEntity.ok(articles);
    }

    /**
     * Busca artigos publicados
     */
    @GetMapping("/published")
    public ResponseEntity<List<ArticleResponseDto>> getPublishedArticles() {
        log.info("GET /api/v1/articles/published - Buscando artigos publicados");
        List<ArticleResponseDto> articles = articleService.findPublishedArticles();
        return ResponseEntity.ok(articles);
    }

    /**
     * Busca artigos publicados com paginação
     */
    @GetMapping("/published/paginated")
    public ResponseEntity<Page<ArticleResponseDto>> getPublishedArticlesPaginated(
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("GET /api/v1/articles/published/paginated - Buscando artigos publicados com paginação");
        Page<ArticleResponseDto> articles = articleService.findPublishedArticles(pageable);
        return ResponseEntity.ok(articles);
    }

    /**
     * Busca artigo por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long id) {
        log.info("GET /api/v1/articles/{} - Buscando artigo por ID", id);
        ArticleResponseDto article = articleService.findById(id);
        return ResponseEntity.ok(article);
    }

    /**
     * Busca artigos por título
     */
    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponseDto>> searchArticlesByTitle(
            @RequestParam String title) {
        log.info("GET /api/v1/articles/search?title={} - Buscando artigos por título", title);
        List<ArticleResponseDto> articles = articleService.findByTitle(title);
        return ResponseEntity.ok(articles);
    }

    /**
     * Busca artigos publicados por título
     */
    @GetMapping("/published/search")
    public ResponseEntity<List<ArticleResponseDto>> searchPublishedArticlesByTitle(
            @RequestParam String title) {
        log.info("GET /api/v1/articles/published/search?title={} - Buscando artigos publicados por título", title);
        List<ArticleResponseDto> articles = articleService.findPublishedByTitle(title);
        return ResponseEntity.ok(articles);
    }

    /**
     * Busca artigos por autor
     */
    @GetMapping("/author/{author}")
    public ResponseEntity<List<ArticleResponseDto>> getArticlesByAuthor(@PathVariable String author) {
        log.info("GET /api/v1/articles/author/{} - Buscando artigos por autor", author);
        List<ArticleResponseDto> articles = articleService.findByAuthor(author);
        return ResponseEntity.ok(articles);
    }

    /**
     * Cria um novo artigo
     */
    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleRequestDto requestDto) {
        log.info("POST /api/v1/articles - Criando novo artigo: {}", requestDto.getTitle());
        ArticleResponseDto createdArticle = articleService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    /**
     * Atualiza um artigo existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Long id, 
            @Valid @RequestBody ArticleRequestDto requestDto) {
        log.info("PUT /api/v1/articles/{} - Atualizando artigo", id);
        ArticleResponseDto updatedArticle = articleService.update(id, requestDto);
        return ResponseEntity.ok(updatedArticle);
    }

    /**
     * Exclui um artigo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        log.info("DELETE /api/v1/articles/{} - Excluindo artigo", id);
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Publica um artigo
     */
    @PatchMapping("/{id}/publish")
    public ResponseEntity<ArticleResponseDto> publishArticle(@PathVariable Long id) {
        log.info("PATCH /api/v1/articles/{}/publish - Publicando artigo", id);
        ArticleResponseDto publishedArticle = articleService.publish(id);
        return ResponseEntity.ok(publishedArticle);
    }

    /**
     * Despublica um artigo
     */
    @PatchMapping("/{id}/unpublish")
    public ResponseEntity<ArticleResponseDto> unpublishArticle(@PathVariable Long id) {
        log.info("PATCH /api/v1/articles/{}/unpublish - Despublicando artigo", id);
        ArticleResponseDto unpublishedArticle = articleService.unpublish(id);
        return ResponseEntity.ok(unpublishedArticle);
    }
}
