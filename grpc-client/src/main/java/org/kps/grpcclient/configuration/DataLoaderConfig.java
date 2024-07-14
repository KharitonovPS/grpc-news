//package org.kps.grpcclient.configuration;
//
//import com.google.common.util.concurrent.ListenableFuture;
//import lombok.RequiredArgsConstructor;
//import org.dataloader.DataLoader;
//import org.dataloader.DataLoaderRegistry;
//import org.dataloader.MappedBatchLoader;
//import org.kps.grpc.AuthorServiceGrpc;
//import org.kps.grpc.TaskService;
//import org.kps.grpcclient.author.mapper.AuthorMapper;
//import org.kps.grpcclient.utils.FutureConverter;
//import org.kps.grpcmodel.model.Author;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.graphql.execution.BatchLoaderRegistry;
//import org.springframework.graphql.execution.DefaultBatchLoaderRegistry;
//
//import java.util.stream.Collectors;
//
//@Configuration
//@RequiredArgsConstructor
//public class DataLoaderConfig {
//
//    private final AuthorServiceGrpc.AuthorServiceFutureStub futureStub;
//    private final AuthorMapper authorMapper;
//
//    @Bean
//    public BatchLoaderRegistry batchLoaderRegistry() {
//        BatchLoaderRegistry batchLoaderRegistry = new DefaultBatchLoaderRegistry();
//
//        MappedBatchLoader<Long, Author> mappedBatchLoader = ids -> {
//            TaskService.AuthorIdsRequest request = TaskService.AuthorIdsRequest.newBuilder().addAllId(ids).build();
//            ListenableFuture<TaskService.AuthorListResponse> futureResponse = futureStub.findAuthors(request);
//            return FutureConverter.toCompletableFuture(futureResponse)
//                    .thenApply(response -> response.getAuthorList().stream()
//                            .collect(Collectors.toMap(TaskService.Author::getId, authorMapper::toAuthor)
//                            ));
//        };
//
//        batchLoaderRegistry.forTypePair(Long.class, Author.class).registerMappedBatchLoader(mappedBatchLoader);
//        return batchLoaderRegistry;
//    }
//
//}
