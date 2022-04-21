package devel0per36.videolibrary.server.action;

import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;
import devel0per36.videolibrary.person.User;

import java.util.List;

/**
 * Интерфейс для описания базовых действий административного персонала ресурса
 * @version 1.0
 */
public interface BaseAdminAction {
    /**
     * Создание видеотеки
     * @param name - наименование
     * @param movies - список фильмов
     * @param user - пользователь
     * @return возвращает логический ответ о том, создана ли видеотека
     */
    boolean createVideoLibrary(String name, List<Movie> movies, User user);

    /**
     * Удаление видеотеки
     * @param library - библиотека
     * @param user - пользователь
     * @return возвращает логический ответ о том, удалена ли видеотека
     */
    boolean deleteVideoLibrary(Library library, User user);
}
