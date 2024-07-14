package org.kps.grpcclient.author.client;

import io.grpc.Deadline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.author.mapper.AuthorMapper;
import org.kps.grpcclient.utils.FutureConverter;
import org.kps.grpcmodel.model.Author;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        )).findAuthor(request));
        return completableFuture.thenApply(mapper::toAuthor);
    }

    public CompletableFuture<Map<Long, Author>> findAuthorsByIds(Set<Long> authorIds) {
        TaskService.AuthorIdsRequest request = TaskService.AuthorIdsRequest.newBuilder()
                .addAllId(authorIds).build();

        CompletableFuture<TaskService.AuthorListResponse> completableFuture = FutureConverter.toCompletableFuture(futureStub.withDeadline(Deadline.after(
                4000L,
                TimeUnit.MILLISECONDS
        )).findAuthors(request));
        return completableFuture.thenApply(authorListResponse -> authorListResponse.getAuthorList()
                .stream().collect(Collectors.toMap(TaskService.Author::getId, mapper::toAuthor)
                ));
    }

}
