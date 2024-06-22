package org.kps.grpcclient.controller;

import org.kps.grpcclient.service.Author;
import org.kps.grpcclient.service.News;
import org.kps.grpcclient.service.NewsInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController {

    @QueryMapping
    public News newsById(@Argument String id) {
        return News.getById(id);
    }

    @QueryMapping
    public List<News> findAll() {
        return News.findAll();
    }

    @MutationMapping
    //можно принимать много парамов и на каждый вешать @Argument
    public News addNews(@Argument NewsInput newsInput) {
        Author author = Author.getById(newsInput.authorId());
        var newNews = new News("newId", newsInput.name(), newsInput.pageCount(), newsInput.rating(), author.id());
        return News.create(newNews);
    }

    @MutationMapping
    public News updateNews(@Argument String id, @Argument NewsInput newsInput) {
        return News.updateNews(id, newsInput.name(), newsInput.pageCount(), newsInput.rating(), newsInput.authorId());
    }

    @MutationMapping
    public News delete(@Argument String id) {
        return News.deleteById(id);
    }

    @SchemaMapping
    public Author author(News news) {
        return Author.getById(news.authorId());
    }

//    @SchemaMapping
//    public Rating rating(News news){
//        return
//    }
}
