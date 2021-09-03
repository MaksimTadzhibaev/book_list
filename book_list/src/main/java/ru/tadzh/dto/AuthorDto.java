package ru.tadzh.dto;

/*
Транспортировка информации в нужном нам виде из БД в сервисный слой
 */

public class AuthorDto {

    private Long id;

    private String fullName;

    private Integer birthDate;

    public AuthorDto() {
    }

    public AuthorDto(Long id) {
        this.id = id;
    }

    public AuthorDto(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public AuthorDto(Long id, String fullName, Integer birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }
}
