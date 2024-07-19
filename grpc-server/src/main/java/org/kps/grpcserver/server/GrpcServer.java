package org.kps.grpcserver.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpcserver.author.service.AuthorCompositeService;
import org.kps.grpcserver.common.intersceptor.ServerLoggingIntersceptor;
import org.kps.grpcserver.configuration.props.GrpcServerProperties;
import org.kps.grpcserver.news.service.NewsCompositeService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class GrpcServer {

    private final NewsCompositeService newsService;
    private final AuthorCompositeService authorService;
    private final GrpcServerProperties grpcServerProperties;
    private Server server;

    @EventListener(ApplicationReadyEvent.class)
    private void startUp() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(grpcServerProperties.port())
                .addService(newsService)
                .addService(authorService)
                .intercept(new ServerLoggingIntersceptor())
                .build();
        server.start();
        log.info("Server started...");

        server.awaitTermination();
    }

    @PreDestroy
    private void shutDown() {
        server.shutdownNow();
    }

}
