package org.kps.grpcserver.configuration.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc")
public record GrpcServerProperties (
        String host,
        int port,
        Boolean isAsync
){
}
