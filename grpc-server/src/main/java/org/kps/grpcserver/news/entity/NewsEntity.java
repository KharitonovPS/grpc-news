package org.kps.grpcserver.news.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.kps.grpcmodel.model.Rating;
import org.kps.grpcserver.author.entity.AuthorEntity;
import org.kps.grpcserver.common.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewsEntity extends BaseEntity {

    private String name;
    private int pageCount;
    private Rating rating;
    private AuthorEntity author;
}

