package com.aronalvarenga.blog.repository;

import com.aronalvarenga.blog.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * Busca artigos publicados
     */
    List<Article> findByIsPublishedTrue();

    /**
     * Busca artigos publicados com paginação
     */
    Page<Article> findByIsPublishedTrue(Pageable pageable);

    /**
     * Busca artigos por autor
     */
    List<Article> findByAuthor(String author);

    /**
     * Busca artigos por título (case insensitive)
     */
    @Query("SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Article> findByTitleContainingIgnoreCase(@Param("title") String title);

    /**
     * Busca artigos publicados por título
     */
    @Query("SELECT a FROM Article a WHERE a.isPublished = true AND LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Article> findPublishedByTitleContainingIgnoreCase(@Param("title") String title);

    /**
     * Verifica se existe um artigo com o título especificado
     */
    boolean existsByTitle(String title);

    /**
     * Busca artigo por título exato
     */
    Optional<Article> findByTitle(String title);
}
