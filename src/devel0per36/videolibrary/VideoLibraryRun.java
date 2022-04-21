package devel0per36.videolibrary;

import devel0per36.videolibrary.application.Application;
import devel0per36.videolibrary.application.component.Quality;
import devel0per36.videolibrary.general.component.Country;
import devel0per36.videolibrary.movie.Comment;
import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;
import devel0per36.videolibrary.movie.component.Genre;
import devel0per36.videolibrary.person.EmployeeFilmCrew;
import devel0per36.videolibrary.person.User;
import devel0per36.videolibrary.person.component.*;
import devel0per36.videolibrary.server.AdminPanel;
import devel0per36.videolibrary.server.Server;
import devel0per36.videolibrary.server.component.Status;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Класс для запуска и тестирования приложения
 * @version 1.0
 */
public class VideoLibraryRun {
    public static void main(String[] args) {
        FullName nameAdmin = new FullName("Админ", "Админович");
        PersonalInfo infoAdmin = new PersonalInfo(nameAdmin, LocalDate.MIN, Gender.MALE, Country.JAPAN);
        User admin = new User(1, "admin", Role.ADMIN, infoAdmin);

        Server justServer = new Server(Status.ACTIVE);
        System.out.println(justServer);
        justServer.addUser(admin);
        AdminPanel panel = new AdminPanel(justServer);
        List<Movie> movies = creatingMovieList();
        test1("Библиотека 1", movies, panel, admin); // создание видеотеки
        System.out.println(justServer);
        Library needLibrary = justServer.getLibraries().get(0);
        Optional<Movie> optionalMovie1 = needLibrary.getMovieByName("Фильм 1");
        Movie movie1 = optionalMovie1.get();
        FullName name1 = new FullName("Имя 1", "Фамилия 1");
        PersonalInfo infoName1 = new PersonalInfo(name1, LocalDate.of(1998, 1, 1), Gender.MALE, Country.GERMANY);
        test2(3, infoName1, Position.OPERATOR, needLibrary, movie1, admin, panel);    // добавление сотрудников съёмочной группы к определенному фильму
        System.out.println(justServer);
        EmployeeFilmCrew name1Old = new EmployeeFilmCrew(3, infoName1, Position.OPERATOR);
        PersonalInfo infoName1New = new PersonalInfo(name1, LocalDate.of(1980, 5, 4), Gender.MALE, Country.RUSSIA);
        EmployeeFilmCrew name1New = new EmployeeFilmCrew(3, infoName1New, Position.ACTOR);
        test3(name1Old, name1New, needLibrary, movie1, admin, panel);
        System.out.println(justServer);
        test4(name1New, needLibrary, movie1, admin, panel);
        System.out.println(justServer);
        test5(89, "Фильм 23", 2086, Country.RUSSIA, Genre.COMEDY, 66, needLibrary, admin, panel);
        System.out.println(justServer);
        Movie myFilm = new Movie(89, "Фильм 23", 2086, Country.RUSSIA, Genre.COMEDY, 66);
        Movie myFilmNew = new Movie(89, "Фильм 125", 2022, Country.FRANCE, Genre.ACTION, 78);
        test6(myFilm, myFilmNew, needLibrary, admin, panel);
        System.out.println(justServer);
        test7(myFilmNew, needLibrary, admin, panel);
        System.out.println(justServer);

        FullName name686 = new FullName("Имя 686", "Фамилия 686");
        PersonalInfo infoName686 = new PersonalInfo(name686, LocalDate.of(1989, Month.APRIL, 23), Gender.MALE, Country.USA);
        User userName686 = new User(2, "name686", Role.USER, infoName686);
        justServer.addUser(userName686);

        Application application = new Application(justServer, userName686);
        test8(application, needLibrary);
        test9(movie1, Quality.LOW, needLibrary, application);
    }

    // получение данных с заполненным списком фильмов
    public static List<Movie> creatingMovieList() {
        FullName name686 = new FullName("Имя 686", "Фамилия 686");
        PersonalInfo infoName686 = new PersonalInfo(name686, LocalDate.of(1989, Month.APRIL, 23), Gender.MALE, Country.USA);
        User userName686 = new User(2, "name686", Role.USER, infoName686);
        FullName name10 = new FullName("Имя 10", "Фамилия 10");
        PersonalInfo infoName10 = new PersonalInfo(name10, LocalDate.of(1995, 4, 12), Gender.FEMALE, Country.RUSSIA);
        User userName10 = new User(3, "name10", Role.USER, infoName10);
        FullName name11 = new FullName("Имя 11", "Фамилия 11");
        PersonalInfo infoName11 = new PersonalInfo(name11, LocalDate.of(2000, 11, 4), Gender.MALE, Country.GERMANY);
        User userName11 = new User(4, "name11", Role.USER, infoName11);
        FullName name12 = new FullName("Имя 12", "Фамилия 12");
        PersonalInfo infoName12 = new PersonalInfo(name12, LocalDate.of(1969, Month.OCTOBER, 10), Gender.MALE, Country.FRANCE);
        User userName12 = new User(5, "name12", Role.USER, infoName12);

        Movie movie1 = new Movie(1, "Фильм 1", 2013, Country.RUSSIA, Genre.FANTASY, 81);
        FullName name20 = new FullName("Имя 20", "Фамилия 20");
        PersonalInfo infoName20 = new PersonalInfo(name20, LocalDate.of(1968, 9, 20), Gender.MALE, Country.USA);
        EmployeeFilmCrew employeeName20 = new EmployeeFilmCrew(1, infoName20, Position.DIRECTOR);
        FullName name21 = new FullName("Имя 21", "Фамилия 21");
        PersonalInfo infoName21 = new PersonalInfo(name21, LocalDate.of(1964, 9, 2), Gender.MALE, Country.USA);
        EmployeeFilmCrew employeeName21 = new EmployeeFilmCrew(2, infoName21, Position.ACTOR);
        movie1.getEmployees().add(employeeName20);
        movie1.getEmployees().add(employeeName21);
        Comment commentMovie11 = new Comment("Комментарий 11", 10, LocalDate.now(), userName686);
        Comment commentMovie12 = new Comment("Комментарий 12", 8, LocalDate.now(), userName10);
        Comment commentMovie13 = new Comment("Комментарий 13", 10, LocalDate.now(), userName11);
        Comment commentMovie14 = new Comment("Комментарий 14", 10, LocalDate.now(), userName12);
        movie1.getComments().add(commentMovie11);
        movie1.getComments().add(commentMovie12);
        movie1.getComments().add(commentMovie13);
        movie1.getComments().add(commentMovie14);

        Movie movie2 = new Movie(2, "Фильм2", 2015, Country.CHINA, Genre.FANTASY, 150);
        Comment commentMovie21 = new Comment("Комментарий 22", 10, LocalDate.now(), userName10);
        Comment commentMovie22 = new Comment("Комментарий 23", 2, LocalDate.now(), userName11);
        movie2.getComments().add(commentMovie21);
        movie2.getComments().add(commentMovie22);

        Movie movie3 = new Movie(3, "Фильм 3", 1998, Country.FRANCE, Genre.COMEDY, 60);
        Comment commentMovie31 = new Comment("Комментарий 31", 10, LocalDate.now(), userName686);
        Comment commentMovie32 = new Comment("Комментарий 32", 10, LocalDate.now(), userName10);
        Comment commentMovie33 = new Comment("Комментарий 33", 10, LocalDate.now(), userName12);
        Comment commentMovie34 = new Comment("Комментарий 34", 10, LocalDate.now(), userName11);
        movie3.getComments().add(commentMovie31);
        movie3.getComments().add(commentMovie32);
        movie3.getComments().add(commentMovie33);
        movie3.getComments().add(commentMovie34);

        Movie movie4 = new Movie(4, "Фильм 4", 2001, Country.JAPAN, Genre.ACTION, 83);
        Comment commentMovie41 = new Comment("Коментарий 41", 10, LocalDate.now(), userName11);
        Comment commentMovie42 = new Comment("Коментарий 42", 9, LocalDate.now(), userName10);
        Comment commentMovie43 = new Comment("Коментарий 43", 10, LocalDate.now(), userName686);
        movie4.getComments().add(commentMovie41);
        movie4.getComments().add(commentMovie42);
        movie4.getComments().add(commentMovie43);

        Movie movie5 = new Movie(5, "Фильм 5", 2020, Country.USA, Genre.COMEDY, 85);
        Comment commentMovie51 = new Comment("Комментарий 51", 1, LocalDate.now(), userName12);
        Comment commentMovie52 = new Comment("Комментарий 52", 8, LocalDate.now(), userName11);
        Comment commentMovie53 = new Comment("Комментарий 53", 5, LocalDate.now(), userName686);
        movie5.getComments().add(commentMovie51);
        movie5.getComments().add(commentMovie52);
        movie5.getComments().add(commentMovie53);

        Movie movie6 = new Movie(6, "Фильм 6", 2022, Country.USA, Genre.ACTION, 100);
        Comment commentMovie61 = new Comment("Комментарий 61", 8, LocalDate.now(), userName10);
        Comment commentMovie62 = new Comment("Коммментарий 62", 7, LocalDate.now(), userName12);
        movie6.getComments().add(commentMovie61);
        movie6.getComments().add(commentMovie62);

        Movie movie7 = new Movie(7, "Фильм 7", 2022, Country.CHINA, Genre.FANTASY, 115);
        Comment commentMovie71 = new Comment("Комментарий 71", 3, LocalDate.now(), userName10);
        Comment commentMovie72 = new Comment("Комментарий 72", 4, LocalDate.now(), userName11);
        movie7.getComments().add(commentMovie71);
        movie7.getComments().add(commentMovie72);

        List<Movie> movieList = new LinkedList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
        movieList.add(movie5);
        movieList.add(movie6);
        movieList.add(movie7);

        return movieList;
    }

    // создание библиотеки
    public static void test1(String nameLibrary, List<Movie> movies, AdminPanel panel, User user) {
        panel.createVideoLibrary(nameLibrary, movies, user);
    }

    // создание сотрудника съёмочной группы
    public static void test2(long id, PersonalInfo info, Position position, Library library, Movie movie, User user, AdminPanel panel) {
        panel.createFilmCrewWorker(id, info, position, library, movie, user);
    }

    // изменение данных сотрудника
    public static void test3(EmployeeFilmCrew oldEmployee, EmployeeFilmCrew newEmployee, Library library, Movie movie, User user, AdminPanel panel) {
        panel.editFilmCrewWorker(oldEmployee, newEmployee, library, movie, user);
    }

    // удаление данных сотрудника
    public static void test4(EmployeeFilmCrew employee, Library library, Movie movie, User user, AdminPanel panel) {
        panel.deleteFilmCrewWorker(employee, library, movie, user);
    }

    // добавление нового фильма
    public static void test5(long id, String name, int year, Country country, Genre genre, int duration, Library library, User user, AdminPanel panel) {
        panel.createMovie(id, name, year, country, genre, duration, library, user);
    }

    // изменение данных фильма
    public static void test6(Movie oldMovie, Movie newMovie, Library library, User user, AdminPanel panel) {
        panel.editMovie(oldMovie, newMovie, library, user);
    }

    // удаление фильма
    public static void test7(Movie movie, Library library, User user, AdminPanel panel) {
        panel.deleteMovie(movie, library, user);
    }

    // выводы фильмов по различным параметрам
    public static void test8(Application application, Library library) {
        application.displayInformationAboutMostPopularMovies(library);
        application.displayInformationAboutNewestMovies(library);
        application.displayLibraryMovies(library);
    }

    // открытие фильма, изменение его качества, включение звука, выключение звука и закрытие фильма
    public static void test9(Movie movie, Quality quality, Library library, Application application) {
        application.openMovie(movie, quality, library);
        application.changeVideoQuality(Quality.MEDIUM);
        application.turnOnSound();
        application.turnOffSound();
        application.closeMovie();
    }
}
