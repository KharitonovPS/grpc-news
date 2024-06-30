package org.kps.grpcclient.client;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsClient {

    private final ManagedChannel managedChannel;
    private NewsServiceGrpc.NewsServiceBlockingStub blockingStub;
    @EventListener(ApplicationReadyEvent.class)
    private void initialize(){
        blockingStub = NewsServiceGrpc.newBlockingStub(managedChannel);
    }

//    NewsServiceGrpc.NewsServiceOuterClass.




}
