package ru.tadzh.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.tadzh.dto.AuthorDto;

/*
Конвертация данных с формы из String в AuthorDto (происходит автоматически)
 */

@Component
public class StringToAuthorDtoConverter implements Converter<String, AuthorDto> {

    @Override
    public AuthorDto convert(String id) {
        return new AuthorDto(Long.parseLong(id));
    }
}
