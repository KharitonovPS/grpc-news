package org.kps.grpcclient.client;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.mapper.NewsResponseMapper;
import org.kps.grpcclient.service.News;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsClient {

    private final ManagedChannel managedChannel;
    private final NewsResponseMapper mapper;
    private NewsServiceGrpc.NewsServiceBlockingStub blockingStub;
    @EventListener(ApplicationReadyEvent.class)
    private void initialize(){
        blockingStub = NewsServiceGrpc.newBlockingStub(managedChannel);
    }

    public News findNewsByName(String name){
        TaskService.NewsSelectByNameRequest request = TaskService.NewsSelectByNameRequest.newBuilder()
                .setName(name)
                .build();
        return mapper.toNews(blockingStub.findNewsByName(request));
    }




}
