package org.kps.grpcserver.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {

    private Long id;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private String createdBy = "SYSTEM";
    private String updatedBy = "SYSTEM";

}
