package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.dto.BookDto;
import ru.tadzh.dto.BookListParams;

import java.util.List;
import java.util.Optional;

/*
Интерфейс для реализации сервисного слоя
 */

public interface BookService {
    List<BookDto> findAll();

    Page<BookDto> findWithFilter(BookListParams bookListParams);

    Optional<BookDto> findById(Long id);

    void save(BookDto bookDto);

    void deleteById(Long id);
}
