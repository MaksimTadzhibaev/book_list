package ru.tadzh.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.entity.Book;

/*
Поиск по фильтрам
 */

public class BookSpecifications {

    public static Specification<Book> title(String prefix) {
        return (root, query, builder) -> builder.like(root.get("title"), prefix + "%");
    }

    public static Specification<Book> publicationDate(Integer publicationDate) {
        return (root, query, builder) -> builder.equal(root.get("publicationDate"), publicationDate);
    }

    public static Specification<Book> isbn(Long isbn) {
        return (root, query, builder) -> builder.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> author(String prefix) {
        return (root, query, builder) -> builder.like(root.get("author").get("fullName"), prefix + "%");
    }

    public static Specification<Book> genre(String prefix) {
        return (root, query, builder) -> builder.like(root.get("genre").get("type"), prefix + "%");
    }
}
