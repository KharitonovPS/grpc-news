package org.kps.grpcserver.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    private Long id;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private String createdBy = "SYSTEM";
    private String updatedBy = "SYSTEM";

}
