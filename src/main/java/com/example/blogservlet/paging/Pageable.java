package com.example.blogservlet.paging;

import com.example.blogservlet.sort.Sorter;

public interface Pageable {
    Integer getOffset();
    Integer getLimit();
    Sorter getSorter();

}
