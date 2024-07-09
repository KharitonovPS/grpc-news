package org.kps.grpcmodel.model;


import lombok.Builder;

@Builder
public record News(
        long id,
        String name,
        int pageCount,
        Rating rating,
        Long authorId
){
}
