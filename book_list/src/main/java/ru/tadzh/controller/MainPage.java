package ru.tadzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
Обработка запросов пользователя направленных на главную страницу приложения
 */

@Controller
@RequestMapping("/")
public class MainPage {

    //    переадресация на страницу авторизации

    @GetMapping
    public String loginPage() {
        return "login_page";
    }
}
