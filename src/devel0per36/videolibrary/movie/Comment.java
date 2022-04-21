package devel0per36.videolibrary.movie;

import devel0per36.videolibrary.person.User;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс для описания комментария к фильму
 * @version 1.0
 */
public class Comment {
    private String text;       // текст комментария
    private double rating;     // оценка к фильму
    private LocalDate date;    // дата комментария
    private User user;         // пользователь, который оставил комментарий

    /**
     * Конструктор для создания объекта типа Comment
     * @param text - текст
     * @param rating - оценка
     * @param date - дата
     * @param user - пользователь
     */
    public Comment(String text, double rating, LocalDate date, User user) {
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Double.compare(comment.rating, rating) == 0 && Objects.equals(text, comment.text) && Objects.equals(date, comment.date) && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, rating, date, user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
