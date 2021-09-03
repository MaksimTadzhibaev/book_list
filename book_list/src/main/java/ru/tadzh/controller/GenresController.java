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
import ru.tadzh.dto.GenreDto;
import ru.tadzh.dto.GenreListParams;
import ru.tadzh.service.BookService;
import ru.tadzh.service.GenreService;

import javax.validation.Valid;

/*
Обработка запросов пользователя направленных на работу с "Жанрами книг"
 */

@Controller
@RequestMapping("/genres")
public class GenresController {

    private static final Logger logger = LoggerFactory.getLogger(GenresController.class);

    private final GenreService genreService;
    private final BookService bookService;

    @Autowired
    public GenresController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    //    Отображение страницы содержащей всю информацию про "Жанры книг"

    @GetMapping
    public String listPageGenres(Model model, GenreListParams genreListParams) {
        logger.info("Genre list page requested");
        model.addAttribute("genres", genreService.findWithFilter
                (genreListParams));
        return "genres";
    }

    //    Создание нового "Жанра книг"

    @GetMapping("/new")
    public String newGenreForm(Model model) {
        logger.info("New genre page requested");
        model.addAttribute("genres", new GenreDto());
        return "genre_form";
    }

    //    Редактирование "Жанра книг"

    @GetMapping("/{id}")
    public String editGenre(@PathVariable("id") Long id, Model model) {
        logger.info("Edit genre page requested");
        model.addAttribute("genres", genreService.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found")));
        return "genre_form";
    }

    //    Сохранение нового или сохранение изменений в "Жанре книг"

    @PostMapping
    public String updateGenre(@Valid GenreDto genreDto, BindingResult result, Model model) {
        logger.info("Saving genre or save genre changes");
        if (result.hasErrors()) {
            return "genre_form";
        }
        genreService.save(genreDto);
        return "redirect:/genres";
    }

    //    Удаление "Жанра книг"

    @DeleteMapping("/{id}")
    public String deleteGenre(@PathVariable("id") Long id) {
        logger.info("Delete genre");
        genreService.deleteById(id);
        return "redirect:/genres";
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
