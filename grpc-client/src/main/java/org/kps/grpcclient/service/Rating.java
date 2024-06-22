package org.kps.grpcclient.service;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
    FIVE_STAR,
    FOUR_STAR,
    THREE_STAR,
    TWO_STAR,
    ONE_STAR;

//    private String star;
//
//    Rating(String star) {
//        this.star = star;
//    }

//    @JsonValue
//    public String getRating(){
//        return star;
//    }
}
