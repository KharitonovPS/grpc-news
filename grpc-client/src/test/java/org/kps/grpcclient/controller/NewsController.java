package org.kps.grpcclient.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(NewsController.class)
public class NewsController {

    @Autowired
    private GraphQlTester graphQlTester;


    @Test
    void shouldReturnFirstNews() {
        this.graphQlTester
                    .documentName("newsDetails")
                    .variable("id", "news-1")
                    .execute()
                .path("newsById")
                .matchesJson("""
                        {
                          "data": {
                            "newsById": {
                              "id": "news-1",
                              "name": "Effective Java",
                              "pageCount": 416,
                              "author": {
                                "firstName": "Joshua",
                                "lastName": "Bloch"
                              }
                            }
                          }
                        }
                        """);
    }
}
