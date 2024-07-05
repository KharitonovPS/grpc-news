package org.kps.grpcclient.client;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.mapper.AuthorMapper;
import org.kps.grpcmodel.model.Author;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorClient {

    private final ManagedChannel managedChannel;
    private AuthorServiceGrpc.AuthorServiceBlockingStub blockingStub;
    private final AuthorMapper mapper;

    @EventListener(ApplicationReadyEvent.class)
    private void initialize(){
        blockingStub = AuthorServiceGrpc.newBlockingStub(managedChannel);
    }

    public Author findById(Long id) {
        TaskService.AuthorRequest request = TaskService.AuthorRequest.newBuilder()
                .setId(id).build();

        return mapper.toAuthor(blockingStub.findAuthor(request));
    }
}
