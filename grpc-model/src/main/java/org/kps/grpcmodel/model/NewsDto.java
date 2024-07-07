package org.kps.grpcmodel.model;


import lombok.Builder;

@Builder
public record NewsDto(
        String id,
        String name,
        int pageCount,
        Rating rating,
        Long authorId
){
}
