package com.example.blogservlet.dto;

import com.example.blogservlet.model.Abstract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Part;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO extends Abstract<NewsDTO> {
    private Long id;
    private String title;
    private Part thumbnail;
    private String shortDescription;
    private String content;
    private Long categoryId;
    private String createdBy;
    private String modifiedBy;
}
