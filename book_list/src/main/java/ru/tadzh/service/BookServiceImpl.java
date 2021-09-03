package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tadzh.dto.*;
import ru.tadzh.entity.Author;
import ru.tadzh.entity.Book;
import ru.tadzh.entity.Genre;
import ru.tadzh.repository.BookRepository;
import ru.tadzh.specification.BookSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Слой получения данных из репозитория, обработка и отправка контроллеру
 */

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //  Получение списка всех книг из БД

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDto(book.getId(),
                        book.getTitle(),
                        book.getPublicationDate(),
                        book.getIsbn(),
                        new AuthorDto(book.getAuthor().getId(), book.getAuthor().getFullName(), book.getAuthor().getBirthDate()),
                        new GenreDto(book.getGenre().getId(), book.getGenre().getType())))
                .collect(Collectors.toList());
    }

    //   Получение списка всех книг из БД с пропуском через параметры фильтрации, сортировки и пагинации

    @Override
    public Page<BookDto> findWithFilter(BookListParams bookListParams) {
        Specification<Book> spec = Specification.where(null);

        if (bookListParams.getTitleFilter() != null && !bookListParams.getTitleFilter().isBlank()) {
            spec = spec.and(BookSpecifications.title(bookListParams.getTitleFilter()));
        }
        if (bookListParams.getPublicationDateFilter() != null) {
            spec = spec.and(BookSpecifications.publicationDate(bookListParams.getPublicationDateFilter()));
        }
        if (bookListParams.getIsbnFilter() != null) {
            spec = spec.and(BookSpecifications.isbn(bookListParams.getIsbnFilter()));
        }
        if (bookListParams.getAuthorFilter() != null && !bookListParams.getAuthorFilter().isBlank()) {
            spec = spec.and(BookSpecifications.author(bookListParams.getAuthorFilter()));
        }
        if (bookListParams.getGenreFilter() != null && !bookListParams.getGenreFilter().isBlank()) {
            spec = spec.and(BookSpecifications.genre(bookListParams.getGenreFilter()));
        }

        Sort sort;
        if (bookListParams.getSorting() != null && !bookListParams.getSorting().isBlank()) {
            sort = Sort.by(bookListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (bookListParams.getSortingParam() != null && !bookListParams.getSortingParam().isBlank() && bookListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return bookRepository.findAll(spec,
                PageRequest.of(
                        Optional.ofNullable(bookListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(bookListParams.getSize()).orElse(3),
                        sort)).map(book -> new BookDto(
                                book.getId(),
                                book.getTitle(),
                                book.getPublicationDate(),
                                book.getIsbn(),
                                new AuthorDto(book.getAuthor().getId(), book.getAuthor().getFullName()),
                                new GenreDto(book.getGenre().getId(), book.getGenre().getType())));
    }

    //  Получение книги из БД по id

    @Override
    public Optional<BookDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getPublicationDate(),
                        book.getIsbn(),
                        new AuthorDto(book.getAuthor().getId(), book.getAuthor().getFullName(), book.getAuthor().getBirthDate()),
                        new GenreDto(book.getGenre().getId(), book.getGenre().getType())));
    }

    //  Сохранение книги в БД

    @Override
    public void save(BookDto bookDto) {
        AuthorDto authorDto = bookDto.getAuthorDto();
        GenreDto genreDto = bookDto.getGenreDto();
        Book book = new Book(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getPublicationDate(),
                bookDto.getIsbn(),
                new Author(authorDto.getId(), authorDto.getFullName(), authorDto.getBirthDate()),
                new Genre(genreDto.getId(), genreDto.getType()));
        bookRepository.save(book);
    }

    //  Удаление книги из БД

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
