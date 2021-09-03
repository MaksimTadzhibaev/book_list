package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.dto.AuthorDto;
import ru.tadzh.dto.AuthorListParams;

import java.util.List;
import java.util.Optional;

/*
Интерфейс для реализации сервисного слоя
 */

public interface AuthorService {

    List<AuthorDto> findAll();

    Page<AuthorDto> findWithFilter(AuthorListParams authorListParams);

    Optional<AuthorDto> findById(Long id);

    void save(AuthorDto authorsDto);

    void deleteById(Long id);
}
