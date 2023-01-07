package com.technical.kardex.domain.models;

import lombok.Data;

import java.util.List;


@Data
public class PageableEntity<T> {

    private List<T> list;
    private int currentPage;
    private int hitsPerPage;
    private long totalHits;


}
