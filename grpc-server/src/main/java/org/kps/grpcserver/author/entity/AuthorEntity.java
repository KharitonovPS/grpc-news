package org.kps.grpcserver.author.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.kps.grpcserver.common.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthorEntity extends BaseEntity {

    private String firstName;
    private String lastName;

    //todo add list
    //private List<NewsEntity> news;
}
