package org.kps.grpcmodel.model;

public record NewsInput(
        String name,
        int pageCount,
        Rating rating,
        String authorId
) {
}
