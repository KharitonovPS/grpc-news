package org.kps.grpcmodel.model;

import lombok.Builder;

@Builder
public record AuthorDto(
        String id,
        String firstName,
        String lastName
) {
}
