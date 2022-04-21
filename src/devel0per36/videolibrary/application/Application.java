package devel0per36.videolibrary.application;

import devel0per36.videolibrary.application.action.MovieAction;
import devel0per36.videolibrary.application.action.OutputMovieAction;
import devel0per36.videolibrary.application.action.VideoPlayerAction;
import devel0per36.videolibrary.application.component.Quality;
import devel0per36.videolibrary.movie.Library;
import devel0per36.videolibrary.movie.Movie;
import devel0per36.videolibrary.person.User;
import devel0per36.videolibrary.server.Server;
import devel0per36.videolibrary.server.component.Status;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Класс для описания приложения для работы с видеотекой
 * @version 1.0
 */
public class Application implements MovieAction, OutputMovieAction {
    private Server server;         // сервер
    private User user;             // пользователь приложения
    private VideoPlayer player;    // видеоплеер

    /**
     * Внутрений класс Видеоплеер
     */
    private class VideoPlayer implements VideoPlayerAction {
        private Movie movie;        // фильм
        private Quality quality;    // качество фильма
        private boolean sound;      // звук у фильма
        private boolean end;        // закончил ли смотреть фильм пользователь

        /**
         * Конструктор внутреннего класса для создания объекта типа VideoPlayer
         * @param movie - фильм
         * @param quality - качество
         */
        public VideoPlayer(Movie movie, Quality quality) {
            this.movie = movie;
            this.quality = quality;
            this.sound = true;
            this.end = false;
        }

        @Override
        public boolean changeVideoQuality(Quality quality) {
            if (server.getStatus() == Status.ACTIVE) {
                if (this.quality == quality) {
                    System.out.println("Качество не изменено, вы выбрали тоже самое качество");
                    return false;
                }
                this.quality = quality;
                System.out.println("Качество изменено на " + quality.getName());
                return true;
            } else {
                System.out.println("Сервер недоступен");
                return false;
            }
        }

        @Override
        public void turnOnSound() {
            if (server.getStatus() == Status.ACTIVE) {
                if (this.sound == true) {
                    System.out.println("У вас уже включен звук");
                } else {
                    this.sound = true;
                    System.out.println("Вы включили звук");
                }
            } else {
                System.out.println("Сервер недоступен");
            }
        }

        @Override
        public void turnOffSound() {
            if (server.getStatus() == Status.ACTIVE) {
                if (this.sound == false) {
                    System.out.println("У вас уже выключен звук");
                } else {
                    this.sound = false;
                    System.out.println("Вы выключили звук");
                }
            } else {
                System.out.println("Сервер недоступен");
            }
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        public Quality getQuality() {
            return quality;
        }

        public void setQuality(Quality quality) {
            this.quality = quality;
        }

        public boolean isSound() {
            return sound;
        }

        public void setSound(boolean sound) {
            this.sound = sound;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VideoPlayer that = (VideoPlayer) o;
            return sound == that.sound && end == that.end && Objects.equals(movie, that.movie) && quality == that.quality;
        }

        @Override
        public int hashCode() {
            return Objects.hash(movie, quality, sound, end);
        }

        @Override
        public String toString() {
            return "VideoPlayer{" +
                    "movie=" + movie +
                    ", quality=" + quality +
                    ", sound=" + sound +
                    ", end=" + end +
                    '}';
        }
    }

    /**
     * Конструктор для создания объекта типа Application
     * @param server - сервер
     * @param user - пользователь
     */
    public Application(Server server, User user) {
        this.server = server;
        this.user = user;
    }

    @Override
    public boolean openMovie(Movie movie, Quality quality, Library library) {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if (server.checkLibrary(library)) {
                    if (library.checkMovie(movie)) {
                        if (player != null) {
                            if (player.end == false) {
                                System.out.println("Вы не можете открыть новый не закончив смотреть предыдущий");
                                return false;
                            } else {
                                player.movie = movie;
                                player.quality = quality;
                                player.sound = true;
                                player.end = false;
                                System.out.printf("Вы начали смотреть %s в разрешении %s\n", movie.getName(), quality.getName());
                                return true;
                            }
                        } else {
                            player = new VideoPlayer(movie, quality);
                            System.out.printf("Вы начали смотреть %s в разрешении %s\n", movie.getName(), quality.getName());
                            return true;
                        }
                    } else {
                        System.out.printf("Фильма %s в библиотеке %s не найдено%n", movie.getName(), library.getName());
                        return false;
                    }
                } else {
                    System.out.printf("Библиотеки %s не найдено на сервере%n", library.getName());
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к данном ресурсу");
                return false;
            }
        } else if (server.getStatus() == Status.UNAVAILABLE) {
            System.out.println("Сервер недоступен");
            return false;
        } else {
            System.out.println("Неизвестная ошибка...");
            return false;
        }
    }

    @Override
    public boolean closeMovie() {
        if (server.getStatus() == Status.ACTIVE) {
            if (server.verificationUser(user)) {
                if (player.end == false) {
                    System.out.println("Фильм " + player.movie.getName() + " закрыт");
                    player.end = true;
                    return true;
                } else if ((player == null) || (player.end == true)) {
                    System.out.println("Вы ничего не смотрите чтобы закрывать");
                    return false;
                } else {
                    System.out.println("Неизвестная ошибка");
                    return false;
                }
            } else {
                System.out.println("У вас нет доступа к данном ресурсу");
                return false;
            }
        } else if (server.getStatus() == Status.UNAVAILABLE) {
            System.out.println("Сервер недоступен");
            return false;
        } else {
            System.out.println("Неизвестная ошибка...");
            return false;
        }
    }

    @Override
    public void displayInformationAboutNewestMovies(Library library) {
        if (server.getStatus() == Status.ACTIVE) {
            Optional<Library> optionalLibrary = server.getLibrary(library);
            if (optionalLibrary.isPresent()) {
                Library requestLibrary = optionalLibrary.get();
                List<Movie> requestMovies = requestLibrary.getInformationAboutNewestMovies();
                System.out.println("Список самых новых фильмов: ");
                if (requestMovies.isEmpty()) {
                    System.out.println("Список пуст");
                } else {
                    for (Movie movie : requestMovies) {
                        System.out.printf("%s %s%n", movie.getName(), movie.getYear());
                    }
                }
            } else {
                System.out.println("Запрашиваемой библиотеки фильмов не существует");
            }
        } else {
            System.out.println("Сервер недоступен");
        }
    }

    @Override
    public void displayInformationAboutMostPopularMovies(Library library) {
        if (server.getStatus() == Status.ACTIVE) {
            Optional<Library> optionalLibrary = server.getLibrary(library);
            if (optionalLibrary.isPresent()) {
                Library requestLibrary = optionalLibrary.get();
                List<Movie> requestMovies = requestLibrary.getInformationAboutMostPopularMovies();
                System.out.println("Список самых популярных фильмов: ");
                if (requestMovies.isEmpty()) {
                    System.out.println("Список пуст");
                } else {
                    for (Movie movie : requestMovies) {
                        System.out.printf("%s %s%n", movie.getName(), movie.getYear());
                    }
                }
            } else {
                System.out.println("Запрашиваемой библиотеки фильмов не существует");
            }
        } else {
            System.out.println("Сервер недоступен");
        }
    }

    @Override
    public void displayLibraryMovies(Library library) {
        if (server.getStatus() == Status.ACTIVE) {
            Optional<Library> optionalLibrary = server.getLibrary(library);
            if (optionalLibrary.isPresent()) {
                Library requestLibrary = optionalLibrary.get();
                List<Movie> requestMovies = requestLibrary.getMovies();
                System.out.println("Список всех фильмов: ");
                if (requestMovies.isEmpty()) {
                    System.out.println("Список пуст");
                } else {
                    for (Movie movie : requestMovies) {
                        System.out.printf("%s %s%n", movie.getName(), movie.getYear());
                    }
                }
            } else {
                System.out.println("Запрашиваемой библиотеки фильмов не существует");
            }
        } else {
            System.out.println("Сервер недоступен");
        }
    }

    public void changeVideoQuality(Quality quality) {
        player.changeVideoQuality(quality);
    }

    public void turnOnSound() {
        player.turnOnSound();
    }

    public void turnOffSound() {
        player.turnOffSound();
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VideoPlayer getPlayer() {
        return player;
    }

    public void setPlayer(VideoPlayer player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(server, that.server) && Objects.equals(user, that.user) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, user, player);
    }

    @Override
    public String toString() {
        return "Application{" +
                "server=" + server +
                ", user=" + user +
                ", player=" + player +
                '}';
    }
}
