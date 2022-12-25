package com.aronalvarenga.blog.repository;

import com.aronalvarenga.blog.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
}