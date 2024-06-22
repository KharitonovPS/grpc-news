package org.kps.grpcclient.service;

import lombok.ToString;

@ToString
public enum Rating {
    FIVE_STAR("five"),
    FOUR_STAR("four"),
    THREE_STAR("three"),
    TWO_STAR("two"),
    ONE_STAR("one");

    private final String star;

    Rating(String star) {
        this.star = star;
    }
    //можно настроить RuntimeWiringConfigurer с этой штукой, что бы енамы были более гибкими и принимали стринговое значение
    //но при этом у меня в классе поле не енам а стринга
    public static  Rating fromCustomValue(String star) {
        for (Rating rating : Rating.values()) {
            if (rating.star.equals(star)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown rating: " + star);
    }


}
