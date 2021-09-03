package ru.tadzh.dto;

/*
Транспортировка информации в нужном нам виде из БД в сервисный слой
 */

public class GenreDto {

    private Long id;

    private String type;

    public GenreDto() {
    }

    public GenreDto(Long id) {
        this.id = id;
    }

    public GenreDto(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
