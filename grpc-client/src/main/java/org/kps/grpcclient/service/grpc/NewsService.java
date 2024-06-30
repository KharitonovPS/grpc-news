package org.kps.grpcclient.service.grpc;

import io.grpc.ManagedChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService  {

    private final ManagedChannel managedChannel;


}
