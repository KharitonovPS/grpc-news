package org.kps.grpcserver.news.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.kps.grpcmodel.model.Rating;
import org.kps.grpcserver.common.BaseEntity;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NewsEntity extends BaseEntity {

    private String name;
    private int pageCount;
    private Rating rating;
    private long authorId;
}

