package ru.tadzh.dto;

/*
Параметры фильтрации, пагинации, сортировки
 */

public class BookListParams {

    private String titleFilter;
    private Integer publicationDateFilter;
    private Long isbnFilter;
    private String authorFilter;
    private String genreFilter;
    private Integer page;
    private Integer size;
    private String sorting;
    private String sortingParam;

    public String getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(String titleFilter) {
        this.titleFilter = titleFilter;
    }

    public Integer getPublicationDateFilter() {
        return publicationDateFilter;
    }

    public void setPublicationDateFilter(Integer publicationDateFilter) {
        this.publicationDateFilter = publicationDateFilter;
    }

    public Long getIsbnFilter() {
        return isbnFilter;
    }

    public void setIsbnFilter(Long isbnFilter) {
        this.isbnFilter = isbnFilter;
    }

    public String getAuthorFilter() {
        return authorFilter;
    }

    public void setAuthorFilter(String authorFilter) {
        this.authorFilter = authorFilter;
    }

    public String getGenreFilter() {
        return genreFilter;
    }

    public void setGenreFilter(String genreFilter) {
        this.genreFilter = genreFilter;
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
