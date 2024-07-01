package org.kps.grpcserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@Slf4j
@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(GrpcServerApplication.class, args);
    }

}
