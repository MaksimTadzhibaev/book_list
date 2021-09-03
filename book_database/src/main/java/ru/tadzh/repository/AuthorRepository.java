package ru.tadzh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.tadzh.entity.Author;

/*
Используем методы репозитория для доступа к сущности "Автор книги" в БД
 */

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
}
