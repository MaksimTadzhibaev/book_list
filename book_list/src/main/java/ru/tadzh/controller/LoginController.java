package ru.tadzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
Обработка запросов пользователя при запрете доступа и авторизации
 */

@Controller
public class LoginController {

    //    переадресация на страницу "доступ запрещен"

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

    //    переадресация на страницу авторизации

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }
}
