package org.kps.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.TaskService;
import org.kps.grpc.TasksServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        TasksServiceGrpc.TasksServiceBlockingStub blockingStub = TasksServiceGrpc.newBlockingStub(channel);

        TaskService.TaskRequest request = TaskService.TaskRequest.newBuilder()
                .addAllTask(List.of("CREW-01","CARGO-02"))
                .setDev("Polly")
                .build();

        TaskService.TaskResponse taskResponse = blockingStub.addTasks(request);
        log.info("Take response, {}", taskResponse);

        channel.shutdownNow();

    }

}
