package ru.tadzh.dto;

/*
Параметры фильтрации, пагинации, сортировки
 */

public class AuthorListParams {

    private String fullNameFilter;
    private Integer birthDateFilter;
    private Integer page;
    private Integer size;
    private String sorting;
    private String sortingParam;

    public String getFullNameFilter() {
        return fullNameFilter;
    }

    public void setFullNameFilter(String fullNameFilter) {
        this.fullNameFilter = fullNameFilter;
    }

    public Integer getBirthDateFilter() {
        return birthDateFilter;
    }

    public void setBirthDateFilter(Integer birthDateFilter) {
        this.birthDateFilter = birthDateFilter;
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
