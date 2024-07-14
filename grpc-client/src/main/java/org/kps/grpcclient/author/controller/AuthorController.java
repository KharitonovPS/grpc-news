package org.kps.grpcclient.author.controller;

import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoader;
import org.kps.grpcclient.author.client.AuthorClient;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcmodel.model.News;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorClient authorClient;

    @BatchMapping
    public Mono<Map<News, Author>> author(List<News> news) {
        return null;
    }

        // сущности связаны по id, автор тянется 2 запросом
    @SchemaMapping
    public CompletableFuture<Author> author(News news){
        return authorClient.findById(news.authorId());
    }

    @QueryMapping
    public CompletableFuture<Author> authorById(@Argument Long id) {
        return authorClient.findById(id);
    }
}
