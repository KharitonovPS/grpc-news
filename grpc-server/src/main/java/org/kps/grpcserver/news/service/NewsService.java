package org.kps.grpcserver.news.service;

import lombok.RequiredArgsConstructor;
import org.kps.grpcserver.news.entity.NewsEntity;
import org.kps.grpcserver.news.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    public NewsEntity findByName(String name) {
        return newsRepository.findByName(name);
    }
}
