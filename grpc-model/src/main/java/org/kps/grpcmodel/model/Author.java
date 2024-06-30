package org.kps.grpcmodel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record Author(
        String id,
        String firstName,
        String lastName
) {
}
