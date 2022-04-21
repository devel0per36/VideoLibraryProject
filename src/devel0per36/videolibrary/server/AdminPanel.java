package devel0per36.videolibrary.server;

import devel0per36.videolibrary.general.component.Country;
import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;
import devel0per36.videolibrary.movie.component.Genre;
import devel0per36.videolibrary.person.EmployeeFilmCrew;
import devel0per36.videolibrary.person.User;
import devel0per36.videolibrary.person.component.PersonalInfo;
import devel0per36.videolibrary.person.component.Position;
import devel0per36.videolibrary.person.component.Role;
import devel0per36.videolibrary.server.action.ExtensionAdminAction;
import devel0per36.videolibrary.server.component.Status;

import java.util.List;
import java.util.Objects;

/**
 * Класс для описания панели администратора
 * @version 1.0
 */
public class AdminPanel implements ExtensionAdminAction {
    private Server server;    // сервер

    /**
     * Конструктор для создания объекта типа AdminPanel
     * @param server - сервер
     */
    public AdminPanel(Server server) {
        this.server = server;
    }

    /**
     * Предоставление доступа к ресурсу
     * @param user - пользователь
     */
    public void grantAccess(User user) {
        if (server.getStatus() == Status.ACTIVE) {
            server.addUser(user);
            System.out.printf("Пользователю %s предоставлен доступ к ресурсу%n", user.getLogin());
        } else {
            System.out.println("Сервер недоступен");
        }
    }

    /**
     * Закрытие доступа к ресурсу
     * @param user - пользователь
     */
    public void blockAccess(User user) {
        if (server.getStatus() == Status.ACTIVE) {
            server.blockUser(user);
            System.out.printf("Пользователю %s заблокирован доступ к ресурсу%n", user.getLogin());
        } else {
            System.out.println("Сервер недоступен");
        }
    }

    @Override
    public boolean createVideoLibrary(String name, List<Movie> movies, User user) {
        if (server.getStatus() == Status.ACTIVE) {

        } else {
            System.out.println("Сервер недоступен");
            return false;
        }

        if (this.server.verificationUser(user)) {
            if (user.getRole() == Role.ADMIN) {
                Library library = new Library(name, movies);
                boolean result = server.addLibrary(library);
                if (result) {
                    System.out.println("Библиотека " + library.getName() + " создана на сервере");
                    return true;
                } else {
                    System.out.println("Ошибка! Библиотека " + library.getName() + " не создана на сервере");
                    return false;
                }
            } else {
                System.out.println("Недостаточно прав для совершения операции по созданию видеотеки");
                return false;
            }
        } else {
            System.out.println("У вас нет доступа к ресурсу");
            return false;
        }
    }

    @Override
    public boolean deleteVideoLibrary(Library library, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (this.server.verificationUser(user)) {
                if (user.getRole() == Role.ADMIN) {
                    boolean result = server.deleteLibrary(library);
                    if (result) {
                        System.out.println("Библиотека " + library.getName() + " удалена с сервера");
                        return true;
                    } else {
                        System.out.println("Ошибка! Библиотека " + library.getName() + " не удалена с сервера");
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по удалению видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    @Override
    public boolean createFilmCrewWorker(long id, PersonalInfo info, Position position, Library library, Movie movie, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (this.server.verificationUser(user)) {
                if ((user.getRole() == Role.ADMIN) || (user.getRole() == Role.MODERATOR)) {
                    EmployeeFilmCrew employee = new EmployeeFilmCrew(id, info, position);
                    boolean result = server.addFilmCrewWorker(employee, library, movie);
                    if (result) {
                        System.out.printf("Работник съёмочной группы добавлен в фильм %s\n", movie.getName());
                        return true;
                    } else {
                        System.out.printf("Ошибка! Работник съёмочной группы добавлен в фильм %s\n", movie.getName());
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по удалению видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    @Override
    public boolean editFilmCrewWorker(EmployeeFilmCrew oldEmployee, EmployeeFilmCrew newEmployee, Library library, Movie movie, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if ((user.getRole() == Role.ADMIN) || (user.getRole() == Role.MODERATOR)) {
                    boolean result = this.server.editFilmCrewWorker(oldEmployee, newEmployee, library, movie);
                    if (result) {
                        System.out.println("Данные у сотрудника съёмочной группы с идентификатором " + newEmployee.getId() + " изменены");
                        return true;
                    } else {
                        System.out.println("Данные у сотрудника съёмочной группы с идентификатором " + newEmployee.getId() + " не изменены");
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по созданию видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    @Override
    public boolean deleteFilmCrewWorker(EmployeeFilmCrew employee, Library library, Movie movie, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if ((user.getRole() == Role.ADMIN) || (user.getRole() == Role.MODERATOR)) {
                    boolean result = this.server.deleteFilmCrewWorker(employee, library, movie);
                    if (result) {
                        System.out.println("Сотрудник удален");
                        return true;
                    } else {
                        System.out.println("Сотрудник не удален");
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по созданию видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    @Override
    public boolean createMovie(long id, String name, int year, Country country, Genre genre, int duration, Library library, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if ((user.getRole() == Role.ADMIN) || (user.getRole() == Role.MODERATOR)) {
                    Movie movie = new Movie(id, name, year, country, genre, duration);
                    boolean result = this.server.addMovie(movie, library);
                    if (result) {
                        System.out.println("Фильм " + movie.getName() + " создан в библиотеке " + library.getName());
                        return true;
                    } else {
                        System.out.println("Ошибка! Фильм " + movie.getName() + " не создан в библиотеке " + library.getName());
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по созданию видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    @Override
    public boolean editMovie(Movie oldMovie, Movie newMovie, Library library, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if ((user.getRole() == Role.ADMIN) || (user.getRole() == Role.MODERATOR)) {
                    boolean result = this.server.editMovie(oldMovie, newMovie, library);
                    if (result) {
                        System.out.println("Данные у фильма с идентификатором " + newMovie.getId() + " изменены");
                        return true;
                    } else {
                        System.out.println("Данные у фильма с идентификатором " + newMovie.getId() + " не изменены");
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по созданию видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    @Override
    public boolean deleteMovie(Movie movie, Library library, User user) {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if ((user.getRole() == Role.ADMIN) || (user.getRole() == Role.MODERATOR)) {
                    boolean result = this.server.deleteMovie(movie, library);
                    if (result) {
                        System.out.printf("Фильм %s удален\n", movie.getName());
                        return true;
                    } else {
                        System.out.printf("Фильм %s не удален\n", movie.getName());
                        return false;
                    }
                } else {
                    System.out.println("Недостаточно прав для совершения операции по созданию видеотеки");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к ресурсу");
                return false;
            }
        } else {
            System.out.println("Сервер недоступен");
            return false;
        }
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminPanel that = (AdminPanel) o;
        return Objects.equals(server, that.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server);
    }

    @Override
    public String toString() {
        return "AdminPanel{" +
                "server=" + server +
                '}';
    }
}
