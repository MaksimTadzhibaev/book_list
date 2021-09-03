package ru.tadzh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tadzh.entity.User;

import java.util.Optional;

/*
Используем методы репозитория для доступа к сущности "Пользователь" в БД
 */

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("select distinct u " +
            "from User u " +
            "left join fetch u.roles " +
            "where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
}
