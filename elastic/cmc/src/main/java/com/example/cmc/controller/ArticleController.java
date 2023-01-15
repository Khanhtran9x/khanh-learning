package com.example.cmc.controller;

import com.example.cmc.entity.Article;
import com.example.cmc.repository.ArticleRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity!!!!")
@RequestMapping("api/v1/articles")
public class ArticleController {

    private final ArticleRepository repository;

    @PostMapping("/createOrUpdateArticle")
    public ResponseEntity<Object> createOrUpdateArticle(@RequestBody Article article) throws IOException {
        return new ResponseEntity<>(repository.createOrUpdateDocument(article), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllArticles() throws IOException {
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> getArticleById(@RequestParam String articleId) throws IOException {
        return new ResponseEntity<>(repository.getById(articleId), HttpStatus.FOUND);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }
}
