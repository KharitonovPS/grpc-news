package org.kps.grpcclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
//        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
//                .usePlaintext()
//                .build();
//
//        TasksServiceGrpc.TasksServiceBlockingStub blockingStub = TasksServiceGrpc.newBlockingStub(channel);
//
//        TaskService.TaskRequest request = TaskService.TaskRequest.newBuilder()
//                .addAllTask(List.of("CREW-01","CARGO-02"))
//                .setDev("Polly")
//                .build();
//
//        TaskService.TaskResponse taskResponse = blockingStub.addTasks(request);
//        log.info("Take response, {}", taskResponse);
//
//        channel.shutdownNow();

    }

}
