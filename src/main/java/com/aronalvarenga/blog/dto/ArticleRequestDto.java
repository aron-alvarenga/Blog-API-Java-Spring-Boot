package com.aronalvarenga.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDto {

    @NotBlank(message = "O título do artigo é obrigatório")
    @Size(max = 120, message = "O título deve ter no máximo 120 caracteres")
    private String title;

    @NotBlank(message = "O conteúdo do artigo é obrigatório")
    @Size(max = 5000, message = "O conteúdo deve ter no máximo 5000 caracteres")
    private String content;

    @Size(max = 100, message = "O nome do autor deve ter no máximo 100 caracteres")
    private String author;

    private Boolean isPublished;
}
