package ru.tadzh.controller;

/*
Обработка ошибки при отсутствии сущности
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
