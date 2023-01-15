package com.example.cmc.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.cmc.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ArticleRepository {

    private final ElasticsearchClient elasticsearchClient;

    private final String indexName = "articles";

    public String createOrUpdateDocument(Article article) throws IOException {

        log.info("Performing creating or updating new article: {}", article.toString());
        IndexResponse response = elasticsearchClient.index(
                i -> i.index(indexName)
                        .id(article.getId())
                        .document(article)
        );

        if (response.result().name().equals("Created")) {
            log.info("Created successfully article: {}", article.getTitle());
            return new StringBuilder("Article has been created successfully.").toString();
        } else if (response.result().name().equals("Updated")) {
            log.info("Updated successfully article: {}", article.getTitle());
            return new StringBuilder("Article has been updated successfully.").toString();
        }

        log.error("Error while performing the operation");
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public Article getById(String articleId) throws IOException {

        Article article = null;
        GetResponse<Article> response = elasticsearchClient.get(
                g -> g.index(indexName)
                      .id(articleId)
                      ,Article.class
        );

        if (response.found()) {
            article = response.source();
            assert article != null;
            log.info("Article title is: {}", article.getTitle());
        } else {
            log.error("Article not found");
        }

        return article;
    }

    public List<Article> getAll() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse<Article> searchResponse = elasticsearchClient.search(searchRequest, Article.class);
        List<Hit<Article>> hits = searchResponse.hits().hits();
        List<Article> articles = new ArrayList<>();

        for (Hit<Article> object : hits) {
            log.info(String.valueOf((object.source())));
            articles.add(object.source());
        }

        return articles;
    }
}
