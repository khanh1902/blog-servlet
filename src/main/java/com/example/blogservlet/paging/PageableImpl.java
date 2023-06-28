package com.example.blogservlet.paging;

import com.example.blogservlet.sort.Sorter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageableImpl implements Pageable {
    private Integer offset;
    private Integer limit;
    private Sorter sorter;

    @Override
    public Integer getOffset() {
        if (this.offset != null && this.limit != null)
            return (this.offset - 1) * this.limit;

        return null;
    }

    @Override
    public Integer getLimit() {
        if (this.limit != null)
            return this.limit;
        return null;
    }

    @Override
    public Sorter getSorter() {
        if(this.sorter != null)
            return this.sorter;
        return null;
    }
}
