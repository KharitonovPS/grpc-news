package org.kps.grpcclient.controller;

import lombok.RequiredArgsConstructor;
import org.kps.grpcclient.client.AuthorClient;
import org.kps.grpcmodel.model.AuthorDto;
import org.kps.grpcmodel.model.NewsDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorClient authorClient;

    // todo не работает из-за названия дто, посмотреть на виегу поставить парамы квери маппнг
    @SchemaMapping
    public AuthorDto author(NewsDto news) {
        return authorClient.findAuthor(news);
    }

//    @SchemaMapping
//    public Author author(News news) {
//        return Author.getById(news.authorId());
//    }

}
