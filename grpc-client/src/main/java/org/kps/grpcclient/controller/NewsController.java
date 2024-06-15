package org.kps.grpcclient.controller;

import org.kps.grpcclient.service.Author;
import org.kps.grpcclient.service.News;
import org.kps.grpcclient.service.NewsInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class NewsController {

    @QueryMapping
    public News newsById(@Argument String id) {
        return News.getById(id);
    }

    @MutationMapping
    public News addNews(@Argument NewsInput newsInput){
        Author author = Author.getById(newsInput.authorId());
        return new News("newId",newsInput.name(),newsInput.pageCount(), author.id());
    }

    @SchemaMapping
    public Author author(News news) {
        return Author.getById(news.authorId());
    }
}
