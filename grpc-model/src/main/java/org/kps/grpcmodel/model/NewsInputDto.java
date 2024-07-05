package org.kps.grpcmodel.model;

import lombok.Builder;

@Builder
public record NewsInputDto(
        String name,
        int pageCount,
        Rating rating,
        String authorId
) {
}
