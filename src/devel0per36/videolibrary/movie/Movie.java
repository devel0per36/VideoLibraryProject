package devel0per36.videolibrary.movie;

import devel0per36.videolibrary.general.component.Country;
import devel0per36.videolibrary.movie.component.Genre;
import devel0per36.videolibrary.person.EmployeeFilmCrew;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Класс для описания фильма
 * @version 1.0
 */
public class Movie {
    private long id;                           // идентификатор фильма
    private String name;                       // наименование фильма
    private int year;                          // год производства фильма
    private Country country;                   // страна производства фильма
    private Genre genre;                       // жанр фильма
    private int duration;                      // продолжительность фильма
    private List<EmployeeFilmCrew> employees;  // сотрудники съёмочной группы
    private List<Comment> comments;            // комментария/отзывы к фильму

    /**
     * Конструктор для создания объекта тип Movie
     * @param id - идентификатор
     * @param name - наименование
     * @param year - год
     * @param country - страна
     * @param genre - жанр
     * @param duration - продолжительность
     * @param employees - список сотрудников съёмочной группы
     * @param comments - комментария/отзывы пользователей
     */
    public Movie(long id, String name, int year, Country country, Genre genre, int duration, List<EmployeeFilmCrew> employees, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.duration = duration;
        this.employees = employees;
        this.comments = comments;
    }

    /**
     * Перегруженный конструктор для создания объекта типа Movie
     * @param id - идентификатор
     * @param name - наименование
     * @param year - год
     * @param country - страна
     * @param genre - жанр
     * @param duration - продолжительность
     * @param employees - список сотрудников съёмочной группы
     */
    public Movie(long id, String name, int year, Country country, Genre genre, int duration, List<EmployeeFilmCrew> employees) {
        this(id, name, year, country, genre, duration, employees, new LinkedList<>());
    }

    /**
     * Перегруженный конструктор для создания объекта типа Movie
     * @param id - идентификатор
     * @param name - наименование
     * @param year - год
     * @param country - страна
     * @param genre - жанр
     * @param duration - продолжительность
     */
    public Movie(long id, String name, int year, Country country, Genre genre, int duration) {
        this(id, name, year, country, genre, duration, new LinkedList<>(), new LinkedList<>());
    }

    /**
     * Добавление сотрудника съёмочной группы
     * @param employee - сотрудник
     * @return возвращает логический ответ о том, добавлен ли сотрудник
     */
    public boolean addFilmCrewWorker(EmployeeFilmCrew employee) {
        boolean flag = checkFilmCrewWorker(employee);
        if (!flag) {
            employees.add(employee);
            return true;
        }
        return false;
    }

    /**
     * Изменение данных сотрудника съёмочной группы
     * @param oldEmployee - сотрудник со старыми данными
     * @param newEmployee - сотрудник с новыми данными
     * @return возвращает логический ответ о том, изменены ли данные у сотрудника
     */
    public boolean editFilmCrewWorker(EmployeeFilmCrew oldEmployee, EmployeeFilmCrew newEmployee) {
        boolean flag = checkFilmCrewWorker(oldEmployee);
        if (flag) {
            employees.remove(oldEmployee);
            employees.add(newEmployee);
            return true;
        }
        return false;
    }

    /**
     * Удаление сотрудника съёмочной группы
     * @param employee - сотрудник
     * @return возвращает логический ответ о том, удалён ли сотрудник
     */
    public boolean deleteFilmCrewWorker(EmployeeFilmCrew employee) {
        boolean flag = checkFilmCrewWorker(employee);
        if (flag) {
            employees.remove(employee);
            return true;
        }
        return false;
    }

    /**
     * Проверка на существование сотрудника
     * @param employee - запрашиваемый сотрудник
     * @return возвращает логический ответ о том, существует ли такой сотрудник в списке
     */
    private boolean checkFilmCrewWorker(EmployeeFilmCrew employee) {
        if (employees.isEmpty()) {
            return false;
        }

        for (EmployeeFilmCrew object : employees) {
            if (object.equals(employee)) {
                return true;
            }
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<EmployeeFilmCrew> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeFilmCrew> employees) {
        this.employees = employees;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && year == movie.year && duration == movie.duration && Objects.equals(name, movie.name) && country == movie.country && genre == movie.genre && Objects.equals(employees, movie.employees) && Objects.equals(comments, movie.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, country, genre, duration, employees, comments);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", country=" + country +
                ", genre=" + genre +
                ", duration=" + duration +
                ", employees=" + employees +
                ", comments=" + comments +
                '}';
    }
}
