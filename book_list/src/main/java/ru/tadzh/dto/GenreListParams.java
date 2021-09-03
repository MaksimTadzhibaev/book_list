package ru.tadzh.dto;

/*
Параметры фильтрации, пагинации, сортировки
 */

public class GenreListParams {

    private String typeFilter;
    private Integer page;
    private Integer size;
    private String sorting;
    private String sortingParam;

    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public String getSortingParam() {
        return sortingParam;
    }

    public void setSortingParam(String sortingParam) {
        this.sortingParam = sortingParam;
    }
}
