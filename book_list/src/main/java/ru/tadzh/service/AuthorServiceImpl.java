package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.dto.AuthorDto;
import ru.tadzh.dto.AuthorListParams;
import ru.tadzh.entity.Author;
import ru.tadzh.repository.AuthorRepository;
import ru.tadzh.specification.AuthorSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Слой получения данных из репозитория, обработка и отправка контроллеру
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //  Получение списка всех авторов книг из БД

    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorDto(author.getId(), author.getFullName(), author.getBirthDate()))
                .collect(Collectors.toList());
    }

    //   Получение списка всех авторов книг из БД с пропуском через параметры фильтрации, сортировки и пагинации

    @Override
    public Page<AuthorDto> findWithFilter(AuthorListParams authorListParams) {
        Specification<Author> spec = Specification.where(null);

        if (authorListParams.getFullNameFilter() != null && !authorListParams.getFullNameFilter().isBlank()) {
            spec = spec.and(AuthorSpecifications.fullName(authorListParams.getFullNameFilter()));
        }
        if (authorListParams.getBirthDateFilter() != null) {
            spec = spec.and(AuthorSpecifications.birthDate(authorListParams.getBirthDateFilter()));
        }


        Sort sort;
        if (authorListParams.getSorting() != null && !authorListParams.getSorting().isBlank()) {
            sort = Sort.by(authorListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (authorListParams.getSortingParam() != null && !authorListParams.getSortingParam().isBlank() && authorListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return authorRepository.findAll(spec,
                PageRequest.of(
                        Optional.ofNullable(authorListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(authorListParams.getSize()).orElse(3),
                        sort)).map(author -> new AuthorDto(author.getId(), author.getFullName(), author.getBirthDate()));
    }

    //  Получение автора книг из БД по id

    @Override
    public Optional<AuthorDto> findById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorDto(author.getId(), author.getFullName(), author.getBirthDate()));
    }

    //  Сохранение автора книг в БД

    @Override
    public void save(AuthorDto authorDto) {
        Author author = new Author(authorDto.getId(), authorDto.getFullName(), authorDto.getBirthDate());
        authorRepository.save(author);
    }

    //  Удаление автора книг из БД

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
