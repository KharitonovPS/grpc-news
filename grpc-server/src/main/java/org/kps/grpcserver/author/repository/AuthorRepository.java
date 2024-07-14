package org.kps.grpcserver.author.repository;

import lombok.extern.slf4j.Slf4j;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuthorRepository {

    public AuthorEntity findById(Long id) {
        log.info("Find Author by id: {}", id);
        return createAutor(id);
    }

    private AuthorEntity createAutor(Long id) {
        return AuthorEntity.builder()
                .firstName("test")
                .lastName("testov")
                .id(id)
                .build();
    }

    public List<AuthorEntity> findAuthorsByIds(List<Long> ids) {
        log.info("findAuthorsByIds request {}", ids);
        return ids.stream()
                .map(this::createAutor)
                .toList();
    }
}
