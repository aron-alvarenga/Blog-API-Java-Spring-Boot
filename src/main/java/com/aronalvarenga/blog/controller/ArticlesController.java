package com.aronalvarenga.blog.controller;

import com.aronalvarenga.blog.entity.Articles;
import com.aronalvarenga.blog.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class ArticlesController {

    private ArticlesRepository articlesRepository;

    public ArticlesController(@Autowired ArticlesRepository articlesRepository){
        this.articlesRepository = articlesRepository;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(articlesRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getByID(@PathVariable long id) {
        Optional<Articles> articleByID = articlesRepository.findById(id);

        if(articleByID.isPresent()) {
            return new ResponseEntity(articlesRepository.findById(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Articles article) {
        article.setCreatedAt(new Date());
        return new ResponseEntity(articlesRepository.save(article), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity put(@RequestBody Articles article) {
        Optional<Articles> articleToEdit = articlesRepository.findById(article.getId());

        if(articleToEdit.isPresent()) {
            article.setCreatedAt(new Date());
            return new ResponseEntity(articlesRepository.save(article), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        Optional<Articles> articleToDelete = articlesRepository.findById(id);

        if(articleToDelete.isPresent()) {
            articlesRepository.deleteById(id);
            return new ResponseEntity("Article deleted!", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}

