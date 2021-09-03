package ru.tadzh.dto;

/*
Транспортировка информации в нужном нам виде из БД в сервисный слой
 */

public class BookDto {

    private Long id;

    private String title;

    private Integer publicationDate;

    private Long isbn;

    private AuthorDto authorDto;

    private GenreDto genreDto;

    public BookDto() {
    }

    public BookDto(Long id, String title, Integer publicationDate, Long isbn, AuthorDto authorDto, GenreDto genreDto) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.authorDto = authorDto;
        this.genreDto = genreDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthor(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

    public GenreDto getGenreDto() {
        return genreDto;
    }

    public void setGenre(GenreDto genreDto) {
        this.genreDto = genreDto;
    }
}
