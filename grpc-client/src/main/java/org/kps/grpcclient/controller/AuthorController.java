package org.kps.grpcclient.controller;

import lombok.RequiredArgsConstructor;
import org.kps.grpcclient.client.AuthorClient;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcmodel.model.News;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorClient authorClient;

    @SchemaMapping
    public Author author(News news){
        return authorClient.findById(news.authorId());
    }

    @QueryMapping
    public Author authorById(@Argument Long id) {
        return authorClient.findById(id);
    }
}
