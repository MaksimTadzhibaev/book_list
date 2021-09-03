package ru.tadzh.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.tadzh.dto.RoleDto;

/*
Конвертация данных с формы из String в RoleDto (происходит автоматически)
 */

@Component
public class StringToRoleDtoConverter implements Converter<String, RoleDto> {

    @Override
    public RoleDto convert(String s) {
        String[] arr = s.split(";");
        return new RoleDto(Long.parseLong(arr[0]), arr[1]);
    }
}