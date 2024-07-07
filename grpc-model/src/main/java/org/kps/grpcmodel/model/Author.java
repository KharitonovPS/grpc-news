package org.kps.grpcmodel.model;

import lombok.Builder;

@Builder
public record Author(
        Long id,
        String firstName,
        String lastName
) {
}
