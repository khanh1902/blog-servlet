package com.example.blogservlet.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Abstract<Category> {
    private Long id;
    private String name;
    private String code;
}
