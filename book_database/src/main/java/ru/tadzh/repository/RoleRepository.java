package ru.tadzh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tadzh.entity.Role;

/*
Используем методы репозитория для доступа к сущности "Роль" в БД
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
}
