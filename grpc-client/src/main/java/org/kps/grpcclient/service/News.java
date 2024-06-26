package org.kps.grpcclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record News(String id, String name, int pageCount, Rating rating, String authorId) {

    private static List<News> NEWS = new ArrayList<>();

    static {
        NEWS.add(new News("news-1", "Effective Java", 416, Rating.FIVE_STAR, "author-1"));
        NEWS.add(new News("news-2", "Hitchhiker's Guide to the Galaxy", 208, Rating.THREE_STAR, "author-2"));
        NEWS.add(new News("news-3", "Down Under", 436, Rating.FOUR_STAR, "author-3"));
    }

    public static News create(News newNews) {
        NEWS.add(newNews);
        return newNews;
    }

    public static List<News> findAll() {
        return NEWS;
    }

    public static void deleteAll(){
        NEWS.clear();
    }

    public static void init(){
        NEWS.add(new News("news-1", "Effective Java", 416, Rating.FIVE_STAR, "author-1"));
        NEWS.add(new News("news-2", "Hitchhiker's Guide to the Galaxy", 208, Rating.THREE_STAR, "author-2"));
        NEWS.add(new News("news-3", "Down Under", 436, Rating.FOUR_STAR, "author-3"));
    }

    public static News updateNews(String id, String name, int page, Rating rating, String authorId) {
        News updatedNews = new News(id, name, page, rating, authorId);
        Optional<News> newsOptional = NEWS.stream().filter(news -> news.id.equals(id)).findFirst();
        newsOptional.ifPresentOrElse(n -> {
            var news = newsOptional.get();
            int index = NEWS.indexOf(news);
            NEWS.set(index, updatedNews);
        }, () -> new IllegalArgumentException("Id dose not exist"));
        return updatedNews;
    }

    public static News getById(String id) {
        return NEWS.stream()
                .filter(news -> news.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id dose not exist"));
    }

    public static News deleteById(String id) {
        News news = NEWS.stream()
                .filter(n -> n.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Id dose not exist"));
        NEWS.remove(news);
        return news;
    }


}