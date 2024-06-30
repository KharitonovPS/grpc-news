package org.kps.grpcclient.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc")
public record GrpcClientProperties (
        String host,
        String port,
        Boolean isAsync
){
}
