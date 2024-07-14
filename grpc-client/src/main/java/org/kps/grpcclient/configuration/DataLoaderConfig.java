package org.kps.grpcclient.configuration;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.kps.grpc.AuthorServiceGrpc;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class DataLoaderConfig {

    private final AuthorServiceGrpc.AuthorServiceFutureStub futureStub;


//    @EventListener(ApplicationReadyEvent.class)
//    private void initialize() {
//        futureStub = AuthorServiceGrpc.newFutureStub(managedChannel);
//    }

}
