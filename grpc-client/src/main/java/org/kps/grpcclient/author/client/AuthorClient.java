package org.kps.grpcclient.author.client;

import io.grpc.Deadline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.author.mapper.AuthorMapper;
import org.kps.grpcclient.utils.FutureConverter;
import org.kps.grpcmodel.model.Author;
import org.kps.grpcmodel.model.News;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.kps.grpcclient.utils.TaskExecutor.VIRTUAL_THREAD_TASK_EXECUTOR;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorClient {

    private final AuthorMapper mapper;
    private final AuthorServiceGrpc.AuthorServiceFutureStub futureStub;

    public CompletableFuture<Author> findById(Long id) {
        TaskService.AuthorRequest request = TaskService.AuthorRequest.newBuilder()
                .setId(id).build();

        var completableFuture = FutureConverter.toCompletableFuture(futureStub.withDeadline(Deadline.after(
                        4000L,
                        TimeUnit.MILLISECONDS
                )).withExecutor(VIRTUAL_THREAD_TASK_EXECUTOR)

                                                                            .findAuthor(request));
        return completableFuture.thenApply(mapper::toAuthor);
    }

    public CompletableFuture<Map<News, Author>> findAuthorsByIds(List<News> newsList, Set<Long> authorIds) {
        TaskService.AuthorIdsRequest request = TaskService.AuthorIdsRequest.newBuilder()
                .addAllId(authorIds).build();

        CompletableFuture<TaskService.AuthorListResponse> completableFuture = FutureConverter.toCompletableFuture(
                futureStub.withDeadline(Deadline.after(
                                4000L,
                                TimeUnit.MILLISECONDS
                        ))
                        .withExecutor(VIRTUAL_THREAD_TASK_EXECUTOR)
                        .findAuthors(request));
        return completableFuture.thenApply(authorListResponse -> {
            Map<Long, Author> longAuthorMap = authorListResponse.getAuthorList()
                    .stream().collect(Collectors.toMap(TaskService.Author::getId, mapper::toAuthor)
                    );
            return newsList.stream()
                    .collect(Collectors.toMap(Function.identity(), news -> longAuthorMap.get(news.authorId()))
                    );

        });
    }
}