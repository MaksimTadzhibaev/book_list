package ru.tadzh.service;

import org.springframework.data.domain.Page;
import ru.tadzh.dto.UserDto;
import ru.tadzh.dto.UserListParams;

import java.util.List;
import java.util.Optional;

/*
Интерфейс для реализации сервисного слоя
 */

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
