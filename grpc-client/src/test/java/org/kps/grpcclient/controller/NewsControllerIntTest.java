package org.kps.grpcclient.controller;

import org.junit.jupiter.api.Test;
import org.kps.grpcclient.service.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(NewsController.class)
class NewsControllerIntTest {

    @Autowired
    GraphQlTester graphQlTester;

    @Test
    void newsById() {
    }

    @Test
    void findAllShouldReturnAllNews() {
        var q = """
                query {
                  findAll {
                    id
                    name
                    pageCount
                  }
                }
                """;


        graphQlTester.document(q)
                .execute()
                .path("findAll")
                .entityList(News.class)
                .hasSize(3);

    }

    @Test
    void addNews() {
    }

    @Test
    void updateNews() {
    }

    @Test
    void delete() {
    }
}