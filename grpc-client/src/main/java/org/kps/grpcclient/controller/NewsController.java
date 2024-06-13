package org.kps.grpcclient.controller;

import org.kps.grpcclient.service.Author;
import org.kps.grpcclient.service.News;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class NewsController {

    @QueryMapping
    public News newsById(@Argument String id) {
        return News.getById(id);
    }

    @SchemaMapping
    public Author author(News book) {
        return Author.getById(book.authorId());
    }
}
