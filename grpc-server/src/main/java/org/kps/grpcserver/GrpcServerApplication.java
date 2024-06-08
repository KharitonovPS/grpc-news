package org.kps.grpcserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpcserver.service.TaskServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(GrpcServerApplication.class, args);
        Server server = ServerBuilder.forPort(8080)
                .addService(new TaskServiceImpl() )
                .build();

        server.start();
        log.info("Server started...");

        server.awaitTermination();
    }

}
