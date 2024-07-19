package org.kps.grpcclient.configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.kps.grpc.AuthorServiceGrpc;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpcclient.configuration.properties.GrpcClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PreDestroy;

@Configuration
public class GrpcClientConfig {

    private ManagedChannel managedChannel;

    @Bean
    public ManagedChannel managedChannel(GrpcClientProperties clientProperties) {
        this.managedChannel = ManagedChannelBuilder.forTarget(
                        clientProperties.host() + ":" + clientProperties.port()
                )
                //best practice по умолчанию выбирается первый клиент
                .defaultLoadBalancingPolicy("round_robin")
                .usePlaintext()
                .build();
        return managedChannel;
    }

    @Bean
    public AuthorServiceGrpc.AuthorServiceFutureStub authorServiceFutureStub(GrpcClientProperties clientProperties){
        return AuthorServiceGrpc.newFutureStub(this.managedChannel(clientProperties));
    }

    @Bean
    public NewsServiceGrpc.NewsServiceFutureStub newsServiceFutureStub(GrpcClientProperties clientProperties){
        return NewsServiceGrpc.newFutureStub(this.managedChannel(clientProperties));
    }

    @PreDestroy()
    private void shutDown() {
        managedChannel.shutdownNow();
    }

}
