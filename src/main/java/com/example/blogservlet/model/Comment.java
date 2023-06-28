package com.example.blogservlet.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Abstract<Comment> {
    private Long id;
    private String content;
    private Long userId; //it means createdby
    private Long newId;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String modifiedBy;
}
