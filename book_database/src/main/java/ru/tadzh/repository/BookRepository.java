package ru.tadzh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tadzh.entity.Book;

import java.util.List;
import java.util.Optional;

/*
Используем методы репозитория для доступа к сущности "Книга" в БД
 */

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    //        не работает со спецификацией
    //        @Query(value = "select b from Book b left join fetch b.author left join fetch b.genre",
    //        countQuery = "select count(b) from Book b")
    @EntityGraph(attributePaths = {"author", "genre"})
    Page<Book> findAll(Specification<Book> specification, Pageable var2);
}