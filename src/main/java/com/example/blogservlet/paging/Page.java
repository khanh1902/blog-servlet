package com.example.blogservlet.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private List<T> content = new ArrayList<>();
    private Integer currentPage;
    private Integer maxPageItems;
    private Integer totalItems;
    private Integer totalPages;
    private String sortName;
    private Boolean asc;
    private Long cateId; // paging theo the  loai
}
