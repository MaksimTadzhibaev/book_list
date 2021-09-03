package ru.tadzh.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.entity.Author;

/*
Поиск по фильтрам
 */

public class AuthorSpecifications {

    public static Specification<Author> fullName(String prefix) {
        return (root, query, builder) -> builder.like(root.get("fullName"), prefix + "%");
    }
    public static Specification<Author> birthDate(Integer birthDate) {
        return (root, query, builder) -> builder.equal(root.get("birthDate"), birthDate);
    }
}
