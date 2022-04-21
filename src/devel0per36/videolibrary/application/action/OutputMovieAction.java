package devel0per36.videolibrary.application.action;

import devel0per36.videolibrary.movie.Library;

/**
 * Интерфейс для описания абстрактных методов для вывода фильмов по параметрам
 * @version 1.0
 */
public interface OutputMovieAction {
    /**
     * Вывод самых новых фильмов библиотеки
     * @param library - библиотека фильмов
     */
    void displayInformationAboutNewestMovies(Library library);

    /**
     * Вывод самых популярных фильмов библиотеки
     * @param library - библиотека фильмов
     */
    void displayInformationAboutMostPopularMovies(Library library);

    /**
     * Вывод всех фильмов библиотеки
     * @param library - библиотека фильмов
     */
    void displayLibraryMovies(Library library);
}
