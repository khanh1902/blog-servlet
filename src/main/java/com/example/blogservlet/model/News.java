package com.example.blogservlet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News extends Abstract<News>{
    private Long id;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private Long categoryId;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;
}
