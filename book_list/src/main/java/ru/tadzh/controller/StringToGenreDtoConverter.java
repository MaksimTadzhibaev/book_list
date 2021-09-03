package ru.tadzh.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.tadzh.dto.GenreDto;

/*
Конвертация данных с формы из String в GenreDto (происходит автоматически)
 */

@Component
public class StringToGenreDtoConverter implements Converter<String, GenreDto> {

    @Override
    public GenreDto convert(String id) {
        return new GenreDto(Long.parseLong(id));
    }
}
