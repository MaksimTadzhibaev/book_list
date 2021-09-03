package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.dto.GenreDto;
import ru.tadzh.dto.GenreListParams;
import ru.tadzh.entity.Genre;
import ru.tadzh.repository.GenreRepository;
import ru.tadzh.specification.GenreSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Слой получения данных из репозитория, обработка и отправка контроллеру
 */

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    //  Получение списка всех жанров книг из БД
    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream()
                .map(genre -> new GenreDto(genre.getId(), genre.getType()))
                .collect(Collectors.toList());
    }

    //   Получение списка всех жанров книг из БД с пропуском через параметры фильтрации, сортировки и пагинации

    @Override
    public Page<GenreDto> findWithFilter(GenreListParams genreListParams) {
        Specification<Genre> spec = Specification.where(null);

        if (genreListParams.getTypeFilter() != null && !genreListParams.getTypeFilter().isBlank()) {
            spec = spec.and(GenreSpecifications.type(genreListParams.getTypeFilter()));
        }


        Sort sort;
        if (genreListParams.getSorting() != null && !genreListParams.getSorting().isBlank()) {
            sort = Sort.by(genreListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (genreListParams.getSortingParam() != null && !genreListParams.getSortingParam().isBlank() && genreListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return genreRepository.findAll(spec,
                PageRequest.of(
                        Optional.ofNullable(genreListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(genreListParams.getSize()).orElse(3),
                        sort)).map(genre -> new GenreDto(genre.getId(), genre.getType()));
    }

    //  Получение жанра книг из БД по id

    @Override
    public Optional<GenreDto> findById(Long id) {
        return genreRepository.findById(id)
                .map(genre -> new GenreDto(genre.getId(), genre.getType()));
    }

    //  Сохранение жанра книг в БД

    @Override
    public void save(GenreDto genreDto) {
        Genre genre = new Genre(genreDto.getId(), genreDto.getType());
        genreRepository.save(genre);
    }

    //  Удаление жанра книг из БД

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
