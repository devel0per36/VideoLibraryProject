package devel0per36.videolibrary.application.action;

import devel0per36.videolibrary.application.component.Quality;
import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;

/**
 * Интерфейс для описания абстрактных методов для работы с фильмами
 * @version 1.0
 */
public interface MovieAction {
    /**
     * Открытие фильма
     * @param movie - фильм
     * @param quality - качество
     * @param library - библиотека фильмов откуда берется фильм
     * @return возвращает логический ответ о том, удалось ли открыть фильм
     */
    boolean openMovie(Movie movie, Quality quality, Library library);

    /**
     * Закрытие фильма
     * @return возвращает логический ответ о том, удалось ли закрыть фильм
     */
    boolean closeMovie();
}
