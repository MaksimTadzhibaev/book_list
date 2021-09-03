package ru.tadzh.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.tadzh.entity.Genre;

/*
Поиск по фильтрам
 */

public class GenreSpecifications {

    public static Specification<Genre> type(String prefix) {
        return (root, query, builder) -> builder.like(root.get("type"), prefix + "%");
    }
}
