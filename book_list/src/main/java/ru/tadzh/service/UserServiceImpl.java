package ru.tadzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tadzh.dto.RoleDto;
import ru.tadzh.dto.UserDto;
import ru.tadzh.dto.UserListParams;
import ru.tadzh.entity.Role;
import ru.tadzh.entity.User;
import ru.tadzh.repository.RoleRepository;
import ru.tadzh.repository.UserRepository;
import ru.tadzh.specification.UserSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
Слой получения данных из репозитория, обработка и отправка контроллеру
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //  Получение списка всех пользователей из БД

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(),
                        user.getUsername(),
                        user.getAge()))
                .collect(Collectors.toList());
    }

    //   Получение списка всех пользователей из БД с пропуском через параметры фильтрации, сортировки и пагинации

    @Override
    public Page<UserDto> findWithFilter(UserListParams userListParams) {
        Specification<User> spec = Specification.where(null);

        if (userListParams.getUsernameFilter() != null && !userListParams.getUsernameFilter().isBlank()) {
            spec = spec.and(UserSpecifications.usernamePrefix(userListParams.getUsernameFilter()));
        }
        if (userListParams.getMinAge() != null) {
            spec = spec.and(UserSpecifications.minAge(userListParams.getMinAge()));
        }
        if (userListParams.getMaxAge() != null) {
            spec = spec.and(UserSpecifications.maxAge(userListParams.getMaxAge()));
        }

        Sort sort;
        if (userListParams.getSorting() != null && !userListParams.getSorting().isBlank()) {
            sort = Sort.by(userListParams.getSorting());
        } else {
            sort = Sort.by("id");
        }
        if (userListParams.getSortingParam() != null && !userListParams.getSortingParam().isBlank() && userListParams.getSortingParam().equals("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return userRepository.findAll(spec,
                PageRequest.of(
                        Optional.ofNullable(userListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(userListParams.getSize()).orElse(3), sort))
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getAge()));
    }

    //  Получение пользователя из БД по id

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getAge(), mapRolesDto(user)));
    }

    //  Сохранение пользователя в БД

    @Override
    public void save(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getAge(),
                userDto.getRoles().stream()
                        .map(roleDto -> new Role(roleDto.getId(), roleDto.getName()))
                        .collect(Collectors.toSet()));
        userRepository.save(user);
    }

    //  Удаление пользователя из БД

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private static Set<RoleDto> mapRolesDto(User user) {
        return user.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toSet());
    }
}