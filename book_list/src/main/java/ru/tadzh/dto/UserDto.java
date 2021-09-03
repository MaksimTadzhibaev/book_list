package ru.tadzh.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/*
Транспортировка информации в нужном нам виде из БД в сервисный слой
 */

public class UserDto {

    private Long id;

    @NotBlank
    private String username;

    @Max(value = 120)
    private Integer age;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

    private Set<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public UserDto(Long id, String username, Integer age, Set<RoleDto> roles) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}