package org.kps.grpcclient.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kps.grpcclient.service.News;
import org.kps.grpcclient.service.NewsInput;
import org.kps.grpcclient.service.Rating;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NewsControllerIntTest {

    private HttpGraphQlTester tester;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8080/graphql").build();

        tester = HttpGraphQlTester.create(client);
        News.deleteAll();
        News.init();
    }

    @Test
    @Disabled
    void newsById() {
        tester.documentName("newsById").variable("id", "news-1").execute().path("newsById").matchesJson("""
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
    @Disabled
    void findAllShouldReturnAllNews() {
        tester.documentName("allNews").execute().path("findAll").entityList(News.class).hasSize(3);
    }

    @Test
    @Disabled
    void addNews() {
        tester.documentName("addNews").variable("newsInput", new NewsInput("newName", 1, Rating.FOUR_STAR, "author-1")).execute().path("addNews").matchesJson("""
                {
                      "name": "newName",
                      "pageCount": 1,
                      "rating": "FOUR_STAR",
                      "author": {
                        "firstName": "Joshua",
                        "lastName": "Bloch"
                      }
                    }
                """);
        //why 3?
//        assertEquals(4, News.findAll().size());
    }

    @Test
    @Disabled
    void updateNews() {
        tester.documentName("updNews").variable("newsInput", new NewsInput("updName", 1, Rating.FOUR_STAR, "author-1")).variable("id", "news-2").execute().path("updateNews").matchesJson("""
                {
                      "id": "news-2",
                      "name": "updName",
                      "pageCount": 1,
                      "rating": "FOUR_STAR",
                      "author": {
                        "firstName": "Joshua",
                        "lastName": "Bloch"
                      }
                    }
                """);
        assertEquals(3, News.findAll().size());
    }

    @Test
    @Disabled
    void delete() {

        tester.document("""
                        mutation deleteNews($id: ID) {
                          delete(id: $id) {
                            id
                            name
                            pageCount
                            rating
                            author {
                              firstName
                            }
                          }
                        }
                        """)
                .variable("id", "news-3")
                .execute()
                .path("delete")
                .matchesJson("""
                         {
                               "id": "news-3",
                               "name": "Down Under",
                               "pageCount": 436,
                               "rating": "FOUR_STAR",
                               "author": {
                                 "firstName": "Bill"
                               }
                             }
                        """);
    }
}