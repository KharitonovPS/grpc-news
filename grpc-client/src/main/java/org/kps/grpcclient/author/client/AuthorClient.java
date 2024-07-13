package org.kps.grpcclient.author.client;

import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.author.mapper.AuthorMapper;
import org.kps.grpcclient.utils.FutureConverter;
import org.kps.grpcmodel.model.Author;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorClient {

    private final ManagedChannel managedChannel;
    private final AuthorMapper mapper;
    private AuthorServiceGrpc.AuthorServiceFutureStub futureStub;

    @EventListener(ApplicationReadyEvent.class)
    private void initialize() {
        futureStub = AuthorServiceGrpc.newFutureStub(managedChannel);
    }

    public CompletableFuture<Author> findById(Long id) {
        TaskService.AuthorRequest request = TaskService.AuthorRequest.newBuilder()
                .setId(id).build();

        var completableFuture = FutureConverter.toCompletableFuture(futureStub.withDeadline(Deadline.after(
                4000L,
                TimeUnit.MILLISECONDS
        )).findAuthor(request));
        return completableFuture.thenApply(mapper::toAuthor);
    }

}
