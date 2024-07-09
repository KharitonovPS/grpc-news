package org.kps.grpcserver.author.repository;

import org.kps.grpcmodel.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorRepository {

    public Author findById(Long id){
        return new Author(id, "Dummy", "Boy");
    }
}
