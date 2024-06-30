package org.kps.grpcserver.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpcserver.service.NewsServiceImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class GrpcServer {

    @EventListener(ApplicationReadyEvent.class)
    private void startUp() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8079)
                .addService(new NewsServiceImpl() )
                .build();

        server.start();
        log.info("Server started...");

        server.awaitTermination();
    }

}
