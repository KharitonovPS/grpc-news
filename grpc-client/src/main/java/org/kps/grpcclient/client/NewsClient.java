package org.kps.grpcclient.client;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.mapper.NewsResponseMapper;
import org.kps.grpcmodel.model.News;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<News> findAll() {
        TaskService.Empty request = TaskService.Empty.newBuilder().build();
        var findAllNewsResponse = blockingStub.findAll(request);
        return findAllNewsResponse.getSummaryList().stream().map(mapper::toNews).toList();
    }
}
