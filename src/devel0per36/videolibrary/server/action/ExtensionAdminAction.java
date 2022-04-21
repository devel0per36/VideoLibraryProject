package devel0per36.videolibrary.server.action;

import devel0per36.videolibrary.general.component.Country;
import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;
import devel0per36.videolibrary.movie.component.Genre;
import devel0per36.videolibrary.person.EmployeeFilmCrew;
import devel0per36.videolibrary.person.User;
import devel0per36.videolibrary.person.component.PersonalInfo;
import devel0per36.videolibrary.person.component.Position;

/**
 * Интерфейс для описания расширенных действий административного персонала ресурса
 * @version 1.0
 */
public interface ExtensionAdminAction extends BaseAdminAction {
    /**
     * Создание сотрудника съёмочной группы
     * @param id - идентификатор
     * @param info - личная информация
     * @param position - должность
     * @param library - библиотека
     * @param movie - фильм
     * @param user - пользователь
     * @return возвращает логический ответ о том, создан ли сотрудник
     */
    boolean createFilmCrewWorker(long id, PersonalInfo info, Position position, Library library, Movie movie, User user);

    /**
     * Изменение данных у сотрудника съёмочной группы
     * @param oldEmployee - сотрудник со старыми данными
     * @param newEmployee - сотрудник с новыми данными
     * @param library - библиотека
     * @param movie - фильм
     * @param user - пользователь
     * @return возвращает логический ответ о том, изменены ли данные у сотрудника
     */
    boolean editFilmCrewWorker(EmployeeFilmCrew oldEmployee, EmployeeFilmCrew newEmployee, Library library, Movie movie, User user);

    /**
     * Удаление сотрудника съёмочной группы
     * @param employee - сотрудник
     * @param library - библиотека
     * @param movie - фильм
     * @param user - пользователь
     * @return возвращает логический ответ о том, удален ли сотрудник
     */
    boolean deleteFilmCrewWorker(EmployeeFilmCrew employee, Library library, Movie movie, User user);

    /**
     * Создание фильма
     * @param id - идентификатор
     * @param name - наименование
     * @param year - год
     * @param country - страна
     * @param genre - жанр
     * @param duration - продолжительность
     * @param library - библиотека
     * @param user - пользователь
     * @return возвращает логический ответ о том, создан ли фильм
     */
    boolean createMovie(long id, String name, int year, Country country, Genre genre, int duration, Library library, User user);

    /**
     * Изменение данных у фильма
     * @param oldMovie - фильм со старыми данными
     * @param newMovie - фильм с новыми данными
     * @param library - библиотека
     * @param user - пользователь
     * @return возвращает логический ответ о том, изменены ли данные у фильма
     */
    boolean editMovie(Movie oldMovie, Movie newMovie, Library library, User user);

    /**
     * Удаление фильма
     * @param movie - фильм
     * @param library - библиотека
     * @param user - пользователь
     * @return возвращает логический ответ о том, удален ли фильм
     */
    boolean deleteMovie(Movie movie, Library library, User user);
}
