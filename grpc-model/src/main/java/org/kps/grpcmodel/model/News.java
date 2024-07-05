package org.kps.grpcmodel.model;


import lombok.Builder;

@Builder
public record News (
        String id,
        String name,
        int pageCount,
        Rating rating,
        Long authorId
){
}
