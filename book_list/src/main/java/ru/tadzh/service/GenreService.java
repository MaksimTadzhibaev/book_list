package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.dto.GenreDto;
import ru.tadzh.dto.GenreListParams;

import java.util.List;
import java.util.Optional;

/*
Интерфейс для реализации сервисного слоя
 */

public interface GenreService {

    List<GenreDto> findAll();

    Page<GenreDto> findWithFilter(GenreListParams genreListParams);

    Optional<GenreDto> findById(Long id);

    void save(GenreDto genreDto);

    void deleteById(Long id);
}
