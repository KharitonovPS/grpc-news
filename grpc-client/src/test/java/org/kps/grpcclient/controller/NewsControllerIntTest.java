package org.kps.grpcclient.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kps.grpcclient.service.News;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class NewsControllerIntTest {

    private HttpGraphQlTester tester;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080/graphql")
                .build();

        tester = HttpGraphQlTester.create(client);

    }

    @Test
    void newsById() {
        tester.documentName("newsDetails")
                .variable("id", "news-1")
                .execute()
                .path("newsById")
                .matchesJson("""
                                    {
                                    "id": "news-1",
                                    "name": "Effective Java",
                                    "pageCount": 416,
                                    "rating": "FIVE_STAR",
                                    "author": {
                                        "firstName": "Joshua",
                                        "lastName": "Bloch"
                                        }
                                    }
                        """);
    }

    @Test
    void findAllShouldReturnAllNews() {
        tester.documentName("allNews")
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