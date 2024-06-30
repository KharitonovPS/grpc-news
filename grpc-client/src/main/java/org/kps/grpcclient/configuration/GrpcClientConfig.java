package org.kps.grpcclient.configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.kps.grpcclient.configuration.properties.GrpcClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class GrpcClientConfig {

    private ManagedChannel managedChannel;

    @Bean
    public ManagedChannel managedChannel(GrpcClientProperties clientProperties) {
        this.managedChannel = ManagedChannelBuilder.forTarget(
                        clientProperties.host() + ":" + clientProperties.port()
                )
                .usePlaintext()
                .build();
        return managedChannel;
    }

    @PreDestroy()
    private void shutDown() {
        managedChannel.shutdownNow();
    }


//    TasksServiceGrpc.TasksServiceBlockingStub blockingStub = TasksServiceGrpc.newBlockingStub(channel);
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
