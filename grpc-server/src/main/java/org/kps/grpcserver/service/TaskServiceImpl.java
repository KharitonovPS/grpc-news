package org.kps.grpcserver.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.kps.grpc.TaskService;
import org.kps.grpc.TasksServiceGrpc;
import org.springframework.stereotype.Service;

@Slf4j
public class TaskServiceImpl extends TasksServiceGrpc.TasksServiceImplBase {
    @Override
    public void addTasks(TaskService.TaskRequest request, StreamObserver<TaskService.TaskResponse> responseObserver) {
        log.info(String.valueOf(request));
        var taskList = request.getTaskList().stream().toList();

        //используем билдер для создания ответа
        TaskService.TaskResponse response = TaskService.TaskResponse
                .newBuilder()
                .setTask(taskList + "is approved for dev" + request.getDev())
                .build();

        //отправляем ответ на некст мессагу (одно сообщение в ответ)
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
