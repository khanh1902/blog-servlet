package com.example.blogservlet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Abstract<T> {
    private List<Long> ids = new ArrayList<>(); // danh sach id dung cho cac api xoa
    private List<T> listResult = new ArrayList<>(); // findAll cho view

    //categoryId get AllNews
    private Long cateId;

    // paging
    private Integer offset;
    private Integer limit;
    private Integer totalPages;
    private Integer totalItem; // Tong so bai viet
    private String sortName;
    private Boolean asc;

    private String type; //loai trang
    private String message; // tra ve thong bao CRUD

}
