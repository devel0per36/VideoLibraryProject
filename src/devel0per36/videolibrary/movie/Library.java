package devel0per36.videolibrary.movie;

import devel0per36.videolibrary.person.EmployeeFilmCrew;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс для описания библиотеки фильмов (видеотеки)
 * @version 1.0
 */
public class Library {
    private String name;           // наименование библиотеки
    private List<Movie> movies;    // фильмы

    /**
     * Конструктор для создания объекта типа Library
     * @param name - наименование
     * @param movies - список фильмов
     */
    public Library(String name, List<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    /**
     * Добавление фильма
     * @param movie - фильм
     * @return возвращает логический ответ о том, добавлен ли фильм в список movies
     */
    public boolean addMovie(Movie movie) {
        boolean flag = checkMovie(movie);
        if (!flag) {
            movies.add(movie);
            return true;
        }
        return false;
    }

    /**
     * Редактирование данных фильма
     * @param oldMovie - фильм со старыми данными
     * @param newMovie - фильм с новыми данными
     * @return возвращает ответ о том, изменены ли данные у фильма
     */
    public boolean editMovie(Movie oldMovie, Movie newMovie) {
        boolean flag = checkMovie(oldMovie);
        if (flag) {
            movies.remove(oldMovie);
            movies.add(newMovie);
            return true;
        }
        return false;
    }

    /**
     * Удаление фильма
     * @param movie - фильм
     * @return возвращает логический ответ о том, удален ли фильм
     */
    public boolean deleteMovie(Movie movie) {
        boolean flag = checkMovie(movie);
        if (flag) {
            movies.remove(movie);
            return true;
        }
        return false;
    }

    /**
     * Добавление сотрудника съемочной группы в конкретный фильм
     * @param employee - сотрудник
     * @param movie - фильм
     * @return возвращает логический ответ о том, добавлен ли сотрудник
     */
    public boolean addFilmCrewWorker(EmployeeFilmCrew employee, Movie movie) {
        boolean flag = checkMovie(movie);
        if (flag) {
            int indexMovie = movies.indexOf(movie);
            Movie needMovie = movies.get(indexMovie);
            flag = needMovie.addFilmCrewWorker(employee);
        }
        return flag;
    }

    /**
     * Изменение данных сотрудника съёмочной группы
     * @param oldEmployee - сотрудник со старыми данными
     * @param newEmployee - сотрудник с новыми данными
     * @param movie - фильм
     * @return возвращает логический ответ о том, изменены ли данные у сотрудника
     */
    public boolean editFilmCrewWorker(EmployeeFilmCrew oldEmployee, EmployeeFilmCrew newEmployee, Movie movie) {
        boolean flag = checkMovie(movie);
        if (flag) {
            int indexMovie = movies.indexOf(movie);
            Movie needMovie = movies.get(indexMovie);
            flag = needMovie.editFilmCrewWorker(oldEmployee, newEmployee);
        }
        return flag;
    }

    /**
     * Удаление сотрудника съёмочной группы
     * @param employee - сотрудник
     * @param movie - фильм
     * @return возвращает логический ответ о том, удален ли сотрудник
     */
    public boolean deleteFilmCrewWorker(EmployeeFilmCrew employee, Movie movie) {
        boolean flag = checkMovie(movie);
        if (flag) {
            int indexMovie = movies.indexOf(movie);
            Movie needMovie = movies.get(indexMovie);
            flag = needMovie.deleteFilmCrewWorker(employee);
        }
        return flag;
    }

    /**
     * Проверка на существование фильма
     * @param movie - фильм
     * @return возвращает логический ответ о том, существует ли запрашиваемый фильм
     */
    public boolean checkMovie(Movie movie) {
        if (movies.isEmpty()) {
            return false;
        }

        for (Movie object : movies) {
            if (object.getName().equals(movie.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Получение списка самых новых фильмов
     * @return возвращает список самых новых фильмов
     */
    public List<Movie> getInformationAboutNewestMovies() {
        List<Movie> needMovie = new LinkedList<>();
        int yearNow = LocalDate.now().getYear();
        for (Movie movie : movies) {
            if (movie.getYear() == yearNow) {
                needMovie.add(movie);
            }
        }
        return needMovie;
    }

    /**
     * Получение списка самых популярных фильмов
     * @return возвращает список самых популярных фильмов
     */
    public List<Movie> getInformationAboutMostPopularMovies() {
        List<Movie> needMovie = new LinkedList<>();
        for (Movie movie : movies) {
            if ((averageRating(movie.getComments()) >= 8) && (movie.getComments().size() >= 2)) {
                needMovie.add(movie);
            }
        }
        return needMovie;
    }

    /**
     * Получение фильма по его названию
     * @param name - название фильма
     * @return возвращает оболочку объекта Optional с результатом ответа
     */
    public Optional<Movie> getMovieByName(String name) {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    /**
     * Вычисление среднего арифметического рейтинга
     * @param comments - список комментариев/отзывов
     * @return возвращает среднее арифметическое рейтинга
     */
    private double averageRating(List<Comment> comments) {
        if (comments.isEmpty()) {
            return 0;
        }
        double totalRating = 0;
        for (Comment comment : comments) {
            totalRating += comment.getRating();
        }
        return totalRating / comments.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(name, library.name) && Objects.equals(movies, library.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, movies);
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
