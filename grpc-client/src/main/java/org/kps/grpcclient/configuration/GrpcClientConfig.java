package org.kps.grpcclient.configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.NewsServiceGrpc;
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

    @Bean
    public AuthorServiceGrpc.AuthorServiceFutureStub authorServiceFutureStub(){
        return AuthorServiceGrpc.newFutureStub(managedChannel);
    }

    @Bean
    public NewsServiceGrpc.NewsServiceFutureStub newsServiceFutureStub(){
        return NewsServiceGrpc.newFutureStub(managedChannel);
    }

    @PreDestroy()
    private void shutDown() {
        managedChannel.shutdownNow();
    }

}
