package org.kps.grpcclient.client;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.NewsServiceGrpc;
import org.kps.grpc.TaskService;
import org.kps.grpcclient.mapper.NewsResponseMapper;
import org.kps.grpcmodel.model.News;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsClient {

    private final ManagedChannel managedChannel;
    private final NewsResponseMapper mapper;
    private NewsServiceGrpc.NewsServiceFutureStub futureStub;


    @EventListener(ApplicationReadyEvent.class)
    private void initialize() {
        futureStub = NewsServiceGrpc.newFutureStub(managedChannel)
                .withDeadline(Deadline.after(2000L, TimeUnit.MILLISECONDS));
    }

    public News findNewsByName(String name) {
        TaskService.NewsSelectByNameRequest request = TaskService.NewsSelectByNameRequest.newBuilder()
                .setName(name)
                .build();
        ListenableFuture<TaskService.NewsResponse> listenableFuture = futureStub.findNewsByName(request);

        AtomicReference<TaskService.NewsResponse> holder = new AtomicReference<>();
        Futures.addCallback(listenableFuture, new FutureCallback<TaskService.NewsResponse>() {
            @Override
            public void onSuccess(@Nullable TaskService.NewsResponse result) {
                holder.set(result);
            }

            @Override
            public void onFailure(Throwable t) {
                Status status = Status.fromThrowable(t);
                if (status.getCode() == Status.Code.DEADLINE_EXCEEDED) {
                    log.warn("Request timed out");
                } else {
                    log.error("Request failed %s".formatted(t.getMessage()), t);
                }
            }
        }, new VirtualThreadTaskExecutor(futureStub.getClass() + "-newsClient"));

        return mapper.toNews(holder.get());
    }


    public List<News> findAll() {
//        TaskService.Empty request = TaskService.Empty.newBuilder().build();
//        var findAllNewsResponse = blockingStub.findAll(request);
//        return findAllNewsResponse.getSummaryList().stream().map(mapper::toNews).toList();
        return null;
    }
}
