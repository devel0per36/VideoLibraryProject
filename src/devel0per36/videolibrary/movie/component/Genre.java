package devel0per36.videolibrary.movie.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания жанра
 * @version 1.0
 */
public enum Genre {
    COMEDY("Комедия"), ACTION("Боевик"), FANTASY("Фантастика");

    private String name;    // наименование жанра

    /**
     * Конструктор для объекта перечисления Genre
     * @param name - наименование жанра
     */
    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Genre{", "}")
                .add("name=" + name).toString();
    }
}
