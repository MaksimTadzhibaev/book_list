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
import ru.tadzh.dto.AuthorDto;
import ru.tadzh.dto.AuthorListParams;
import ru.tadzh.service.AuthorService;

import javax.validation.Valid;

/*
Обработка запросов пользователя направленных на работу с "Авторами книг"
 */

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorsController.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //    Отображение страницы содержащей всю информацию про "Авторов книг"

    @GetMapping
    public String listPageAuthors(Model model, AuthorListParams authorListParams) {
        logger.info("Author list page requested");
        model.addAttribute("authors", authorService.findWithFilter
                (authorListParams));
        return "authors";
    }

    //    Создание нового "Автора книг"

    @GetMapping("/new")
    public String newAuthorForm(Model model) {
        logger.info("New author page requested");
        model.addAttribute("authors", new AuthorDto());
        return "author_form";
    }

    //    Редактирование "Автора книг"

    @GetMapping("/{id}")
    public String editAuthor(@PathVariable("id") Long id, Model model) {
        logger.info("Edit author page requested");
        model.addAttribute("authors", authorService.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found")));
        return "author_form";
    }

    //    Сохранение нового или сохранение изменений в "Авторе книг"

    @PostMapping
    public String updateAuthor(@Valid AuthorDto authorDto, BindingResult result, Model model) {
        logger.info("Saving author or save author changes");
        if (result.hasErrors()) {
            return "author_form";
        }
        authorService.save(authorDto);
        return "redirect:/authors";
    }

    //    Удаление "Автора книг"

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        logger.info("Delete author");
        authorService.deleteById(id);
        return "redirect:/authors";
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
