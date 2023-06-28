package com.example.blogservlet.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Abstract<Role> {
    private Long id;
    private String name;
    private String code;
}
