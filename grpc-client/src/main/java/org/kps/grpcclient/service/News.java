package org.kps.grpcclient.service;

import java.util.Arrays;
import java.util.List;

public record News(String id, String name, int pageCount, String authorId) {

    private final static List<News> NEWS = Arrays.asList(
            new News("book-1", "Effective Java", 416, "author-1"),
            new News("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            new News("book-3", "Down Under", 436, "author-3")
    );

    public static News getById(String id) {
        return NEWS.stream()
                .filter(news -> news.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}