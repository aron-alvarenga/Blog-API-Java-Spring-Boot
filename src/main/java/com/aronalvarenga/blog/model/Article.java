package com.aronalvarenga.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do artigo é obrigatório")
    @Size(max = 120, message = "O título deve ter no máximo 120 caracteres")
    @Column(name = "article_title", length = 120, nullable = false)
    private String title;

    @NotBlank(message = "O conteúdo do artigo é obrigatório")
    @Size(max = 5000, message = "O conteúdo deve ter no máximo 5000 caracteres")
    @Column(name = "article_body", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "is_published")
    @Builder.Default
    private Boolean isPublished = false;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
