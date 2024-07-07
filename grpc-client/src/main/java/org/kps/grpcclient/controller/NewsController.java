package org.kps.grpcclient.controller;

import lombok.RequiredArgsConstructor;
import org.kps.grpcclient.client.NewsClient;
import org.kps.grpcmodel.model.News;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsClient client;

    @QueryMapping
    public CompletableFuture<News> newsByName(@Argument String name) {
        return client.findNewsByName(name);
    }

    @QueryMapping
    public  List<News> findAll() {
        return client.findAll();
    }

//    @MutationMapping
//    //можно принимать много парамов и на каждый вешать @Argument, но лучше дто
//    public News addNews(@Argument NewsInput newsInput) {
//        Author author = Author.getById(newsInput.authorId());
//        var newNews = new News("newId", newsInput.name(), newsInput.pageCount(), newsInput.rating(), author.id());
//        return News.create(newNews);
//    }
//
//    @MutationMapping
//    public News updateNews(@Argument String id, @Argument NewsInput newsInput) {
//        return News.updateNews(id, newsInput.name(), newsInput.pageCount(), newsInput.rating(), newsInput.authorId());
//    }
//
//    @MutationMapping
//    public News delete(@Argument String id) {
//        return News.deleteById(id);
//    }


}
