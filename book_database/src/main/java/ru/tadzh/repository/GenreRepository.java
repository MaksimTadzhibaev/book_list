package ru.tadzh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.tadzh.entity.Genre;

/*
Используем методы репозитория для доступа к сущности "Жанр книги" в БД
 */

public interface GenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {

}
