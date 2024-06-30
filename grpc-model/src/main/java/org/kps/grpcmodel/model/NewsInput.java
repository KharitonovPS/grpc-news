package org.kps.grpcmodel.model;

import lombok.Builder;

@Builder
public record NewsInput(
        String name,
        int pageCount,
        Rating rating,
        String authorId
) {
}
