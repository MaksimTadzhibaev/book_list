package ru.tadzh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tadzh.dto.RoleDto;
import ru.tadzh.dto.UserDto;
import ru.tadzh.dto.UserListParams;
import ru.tadzh.repository.RoleRepository;
import ru.tadzh.service.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

/*
Обработка запросов пользователя направленных на работу с "Пользователями"
 */

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    //    Отображение страницы содержащей всю информацию про "Пользователей"

    @GetMapping
    public String listPage(Model model,
                           UserListParams userListParams) {
        logger.info("User list page requested");
        model.addAttribute("users", userService.findWithFilter(userListParams));
        return "users";
    }

    //    Создание нового "Пользователя" другим зарегистрированным пользователем

    @GetMapping("/new")
    public String newUserForm(Model model) {
        logger.info("New user page requested");

        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList()));
        return "user_form";
    }

    //    Редактирование "Пользователя"

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        logger.info("Edit user page requested");
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        model.addAttribute("roles", roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList()));
        return "user_form";
    }

    //    Сохранение новых данных "Пользователя"

    @PostMapping
    public String update(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        logger.info("Saving user");
        if (result.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toList()));
            return "user_form";
        }
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            model.addAttribute("roles", roleRepository.findAll().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toList()));
            result.rejectValue("password", "", "Repeated password is not correct");
            return "user_form";
        }
        userService.save(user);
        return "redirect:/users";
    }

    //    Удаление "Пользователя"

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        logger.info("Deleting user with id {}", id);
        model.addAttribute("user", userService.findById(id));
        userService.deleteById(id);
        return "redirect:/users";
    }

    //    Регистрация "Пользователя"

    @GetMapping("/register")
    public String newUser(Model model) {
        logger.info("New user page requested");
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList()));
        return "register";
    }

    //    Сохдание нового "Пользователя"

    @PostMapping("/new")
    public String updateUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        logger.info("Create user");
        if (result.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toList()));
            return "register";
        }
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            model.addAttribute("roles", roleRepository.findAll().stream()
                    .map(role -> new RoleDto(role.getId(), role.getName()))
                    .collect(Collectors.toList()));
            result.rejectValue("passwordRepeat", "", "Password is wrong");
            return "register";
        }
        userService.save(user);
        return "redirect:/product";
    }

    //  Вывод сообщения об ошибке при отсутствии сущности

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
