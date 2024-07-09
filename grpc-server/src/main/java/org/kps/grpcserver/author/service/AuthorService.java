package org.kps.grpcserver.author.service;

import lombok.RequiredArgsConstructor;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.kps.grpcserver.author.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorEntity findById(long id) {
        return authorRepository.findById(id);
    }
}
