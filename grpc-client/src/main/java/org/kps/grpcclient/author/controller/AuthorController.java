package org.kps.grpcclient.author.controller;

import com.google.api.LabelDescriptorOrBuilder;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.kps.grpcclient.author.client.AuthorClient;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcmodel.model.News;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorClient authorClient;
//    private final DataLoader<Long, Author> authorDataLoader;


//    public AuthorController(BatchLoaderRegistry registry, AuthorClient authorClient) {
//        registry.forTypePair(Long.class, Author.class)
//                .registerMappedBatchLoader((authorIds, env) ->
//                        Mono.fromFuture(authorClient.findAuthorsByIds(authorIds)));
//        this.authorClient = authorClient;
//        this.authorDataLoader= DataLoaderFactory.newDataLoader(                registry.(Long.class, Author.class));

//    }

    @BatchMapping
//    public Mono<Map<News, Author>> author(List<News> news) {
    public Mono<Map<News, Author>> author(List<News> news) {
        Set<Long> authorIds = news.stream()
                .mapToLong(News::authorId)
                .boxed()
                .collect(Collectors.toUnmodifiableSet());

        return  Mono.fromFuture(authorClient.findAuthorsByIds(news,authorIds));
    }


    // сущности связаны по id, автор тянется 2 запросом
    @SchemaMapping
    public CompletableFuture<Author> author(News news) {
        return authorClient.findById(news.authorId());
    }

    @QueryMapping
    public CompletableFuture<Author> authorById(@Argument Long id) {
        return authorClient.findById(id);
    }
}
