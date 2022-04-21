package devel0per36.videolibrary.server;

import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;
import devel0per36.videolibrary.person.EmployeeFilmCrew;
import devel0per36.videolibrary.person.User;
import devel0per36.videolibrary.server.component.Status;

import java.util.*;

/**
 * Класс для описания сервера
 * @version 1.0
 */
public class Server {
    private Status status;               // статус сервера
    private List<Library> libraries;     // библиотеки, содержащиеся на сервере
    private Map<User, Boolean> users;    // пользователи

    /**
     * Конструктор для создания объекта типа Server
     * @param status - статус
     * @param libraries - список библиотек
     * @param users - карта пользователей
     */
    public Server(Status status, List<Library> libraries, Map<User, Boolean> users) {
        this.status = status;
        this.libraries = libraries;
        this.users = users;
    }

    /**
     * Перегруженный конструктор для создания объекта типа Server
     * @param status - статус
     * @param libraries - список библиотек
     */
    public Server(Status status, List<Library> libraries) {
        this(status, libraries, new HashMap<>());
    }

    /**
     * Перегруженный конструктор для создания объекта типа Server
     * @param status - статус
     */
    public Server(Status status) {
        this(status, new LinkedList<>(), new HashMap<>());
    }

    /**
     * Проверка на доступ пользователя
     * @param user - пользователь
     * @return возвращает логический ответ о том, имеет ли доступ к ресурсу пользователь
     */
    public boolean verificationUser(User user) {
        if (users.isEmpty()) {
            return false;
        }

        Set<User> usersSet = users.keySet();
        for (User object : usersSet) {
            if (object.equals(user)) {
                if (users.get(user) == true) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Добавление пользователя для предоставления ему ресурса
     * @param user - пользователь
     */
    public void addUser(User user) {
        users.put(user, true);
    }

    /**
     * Блокировка пользователя
     * @param user - пользователь
     */
    public void blockUser(User user) {
        users.put(user, false);
    }

    /**
     * Добавление библиотеки
     * @param library - библиотека
     * @return возвращает логический ответ о том, добавлена ли библиотека в список
     */
    public boolean addLibrary(Library library) {
        boolean flag = checkLibrary(library);
        if (!flag) {
            libraries.add(library);
            return true;
        }
        return false;
    }

    /**
     * Удаление библиотеки
     * @param library - библиотека
     * @return возвращает логический ответ о том, удалена ли библиотека
     */
    public boolean deleteLibrary(Library library) {
        boolean flag = checkLibrary(library);
        if (flag) {
            libraries.remove(library);
            return true;
        }
        return false;
    }

    /**
     * Добавление фильма
     * @param movie - фильм
     * @param library - библиотека
     * @return @return возвращает логический ответ о том, добавлен ли фильм
     */
    public boolean addMovie(Movie movie, Library library) {
        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            flag = needLibrary.addMovie(movie);
        }
        return flag;
    }

    /**
     * Изменение данных фильма
     * @param oldMovie - фильм со старыми данными
     * @param newMovie - фильм с новыми данными
     * @param library - библиотека
     * @return @return возвращает логический ответ о том, изменены ли данные о фильме
     */
    public boolean editMovie(Movie oldMovie, Movie newMovie, Library library) {
        if (oldMovie.getId() != newMovie.getId()) {
            return false;
        }

        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            flag = needLibrary.editMovie(oldMovie, newMovie);
        }
        return flag;
    }

    /**
     * Удаление фильма
     * @param movie - фильм
     * @param library - библиотека
     * @return возвращает логический ответ о том, удален ли фильм
     */
    public boolean deleteMovie(Movie movie, Library library) {
        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            flag = needLibrary.deleteMovie(movie);
        }
        return flag;
    }


    /**
     * Добавление сотрудника съёмочной группы
     * @param employee - сотрудник
     * @param library - библиотека
     * @param movie - фильм
     * @return возвращает логический ответ о том, создан ли сотрудник
     */
    public boolean addFilmCrewWorker(EmployeeFilmCrew employee, Library library, Movie movie) {
        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            flag = needLibrary.addFilmCrewWorker(employee, movie);
        }
        return flag;
    }

    /**
     * Изменение данных сотрудника съёмочной группы
     * @param oldEmployee - сотрудник со старыми данными
     * @param newEmployee - сотрудник с новыми данными
     * @param library - библиотека
     * @param movie - фильм
     * @return возвращает логический ответ о том, изменены ли данные у сотрудника
     */
    public boolean editFilmCrewWorker(EmployeeFilmCrew oldEmployee, EmployeeFilmCrew newEmployee, Library library, Movie movie) {
        if (oldEmployee.getId() != newEmployee.getId()) {
            return false;
        }

        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            flag = needLibrary.editFilmCrewWorker(oldEmployee, newEmployee, movie);
        }
        return flag;
    }

    /**
     * Удаление сотрудника съёмочной группы
     * @param employee - сотрудник
     * @param library - библиотека
     * @param movie - фильм
     * @return возвращает логический ответ о том, удален ли сотрудник
     */
    public boolean deleteFilmCrewWorker(EmployeeFilmCrew employee, Library library, Movie movie) {
        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            flag = needLibrary.deleteFilmCrewWorker(employee, movie);
        }
        return flag;
    }

    /**
     * Получение библиотеки по наименованию
     * @param library - библиотека
     * @return возвращает Optional запрашиваемого объекта
     */
    public Optional<Library> getLibrary(Library library) {
        boolean flag = checkLibrary(library);
        if (flag) {
            int indexLibrary = libraries.indexOf(library);
            Library needLibrary = libraries.get(indexLibrary);
            return Optional.of(needLibrary);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Проверка на существование библиотеки
     * @param library - библиотека
     * @return возвращает логический ответ о том, существует ли такая библиотека
     */
    public boolean checkLibrary(Library library) {
        if (libraries.isEmpty()) {
            return false;
        }

        for (Library object : libraries) {
            if (object.equals(library)) {
                return true;
            }
        }
        return false;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public Map<User, Boolean> getUsers() {
        return users;
    }

    public void setUsers(Map<User, Boolean> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return status == server.status && Objects.equals(libraries, server.libraries) && Objects.equals(users, server.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, libraries, users);
    }

    @Override
    public String toString() {
        return "Server{" +
                "status=" + status +
                ", libraries=" + libraries +
                ", users=" + users +
                '}';
    }
}
