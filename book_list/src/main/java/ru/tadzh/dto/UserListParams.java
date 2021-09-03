package ru.tadzh.dto;

/*
Параметры фильтрации, пагинации, сортировки
 */

public class UserListParams {

    private String usernameFilter;
    private Integer minAge;
    private Integer maxAge;
    private Integer page;
    private Integer size;
    private String sorting;
    private String sortingParam;

    public String getUsernameFilter() {
        return usernameFilter;
    }

    public void setUsernameFilter(String usernameFilter) {
        this.usernameFilter = usernameFilter;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
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
