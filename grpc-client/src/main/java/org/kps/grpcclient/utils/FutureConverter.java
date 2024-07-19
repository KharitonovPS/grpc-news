package org.kps.grpcclient.utils;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

import static org.kps.grpcclient.utils.TaskExecutor.VIRTUAL_THREAD_TASK_EXECUTOR;

@Slf4j
public class FutureConverter {

    private FutureConverter() {}



    public static <T> CompletableFuture<T> toCompletableFuture(ListenableFuture<T> listenableFuture) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        Futures.addCallback(listenableFuture, new FutureCallback<T>() {

            @Override
            public void onSuccess(T result) {
                completableFuture.complete(result);
            }

            @Override
            public void onFailure(Throwable t) {
                Status status = Status.fromThrowable(t);
                if (status.getCode() == Status.Code.DEADLINE_EXCEEDED) {
                    log.warn("Request timed out");
                } else {
                    log.error("Request failed %s".formatted(t.getMessage()), t);
                    completableFuture.completeExceptionally(t);
                }
            }
        }, VIRTUAL_THREAD_TASK_EXECUTOR);
        return completableFuture;
    }
}
