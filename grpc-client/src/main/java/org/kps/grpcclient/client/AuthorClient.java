package org.kps.grpcclient.client;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.mapper.AuthorMapper;
import org.kps.grpcclient.mapper.NewsResponseMapper;
import org.kps.grpcmodel.model.AuthorDto;
import org.kps.grpcmodel.model.NewsDto;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorClient {

    private final ManagedChannel managedChannel;
    private final AuthorMapper mapper;
    private AuthorServiceGrpc.AuthorServiceBlockingStub blockingStub;


    @EventListener(ApplicationReadyEvent.class)
    private void initialize(){
        blockingStub = AuthorServiceGrpc.newBlockingStub(managedChannel);
    }

    public AuthorDto findAuthor(NewsDto news){
        TaskService.NewsSelectByNameRequest request = TaskService.NewsSelectByNameRequest.newBuilder()
                .setName(news.name())
                .build();
        return mapper.toAuthor(blockingStub.findAuthor(request));
    }


//    public List<NewsDto> findAll() {
//        TaskService.Empty request = TaskService.Empty.newBuilder().build();
//        var findAllNewsResponse = blockingStub.findAll(request);
//        return findAllNewsResponse.getSummaryList().stream().map(mapper::toNews).toList();
//    }
}
