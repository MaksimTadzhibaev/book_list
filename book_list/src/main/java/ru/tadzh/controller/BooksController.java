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
import ru.tadzh.dto.BookDto;
import ru.tadzh.dto.BookListParams;
import ru.tadzh.service.AuthorService;
import ru.tadzh.service.BookService;
import ru.tadzh.service.GenreService;

import javax.validation.Valid;

/*
Обработка запросов пользователя направленных на работу с "Книгами"
 */

@Controller
@RequestMapping("/books")
public class BooksController {

    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;


    @Autowired
    public BooksController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    //    Отображение страницы содержащей всю информацию про "Книги"

    @GetMapping
    public String listPageBooks(Model model, BookListParams bookListParams) {
        logger.info("Book list page requested");
        model.addAttribute("books", bookService.findWithFilter
                (bookListParams));
        return "books";
    }

    //    Создание новой "Книги"

    @GetMapping("/new")
    public String newBookForm(Model model) {
        logger.info("New book page requested");
        model.addAttribute("books", new BookDto());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "book_form";
    }

    //    Редактирование "Книги"

    @GetMapping("/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        logger.info("Edit book page requested");
        model.addAttribute("books", bookService.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found")));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "book_form";
    }

    //    Сохранение новой или сохранение изменений в "Книге"

    @PostMapping
    public String updateBook(@Valid @ModelAttribute("book") BookDto book, BindingResult result, Model model) {
        logger.info("Saving book or save book changes");
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genre", genreService.findAll());
            return "book_form";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    //    Удаление"Книги"

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        logger.info("Delete book");
        bookService.deleteById(id);
        return "redirect:/books";
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
