package org.kps.grpcmodel.model;


import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record News (
        String id,
        String name,
        int pageCount,
        Rating rating,
        String authorId
){
}
