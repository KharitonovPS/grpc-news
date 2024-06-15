package org.kps.grpcclient.service;

public record NewsInput(
        String name,
        int pageCount,
        String authorId
) {
}
