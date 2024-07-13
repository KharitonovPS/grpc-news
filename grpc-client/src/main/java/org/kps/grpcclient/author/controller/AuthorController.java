package org.kps.grpcclient.author.controller;

import lombok.RequiredArgsConstructor;
import org.kps.grpcclient.author.client.AuthorClient;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcmodel.model.News;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorClient authorClient;

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
