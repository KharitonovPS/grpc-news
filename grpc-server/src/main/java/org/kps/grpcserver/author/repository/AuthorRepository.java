package org.kps.grpcserver.author.repository;

import lombok.extern.slf4j.Slf4j;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorRepository {

    public AuthorEntity findById(Long id) {
        log.info("Find Author by id: {}", id);
        return AuthorEntity.builder()
                .firstName("test")
                .lastName("testov")
                .id(id)
                .build();
    }
}
