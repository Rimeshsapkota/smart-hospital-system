package com.smarthospital.common_lib.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class PageResult<T> {
    private Collection<T> content;
    private Integer totalPage;
    private long totalElements;
    private Integer size;
    private Integer page;
    private boolean empty;

    public PageResult(Collection<T> content, long totalElements, Integer totalPage, Integer size, Integer page, boolean empty) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
        this.size = size;
        this.page = page;
        this.empty = empty;
    }
}
