package devel0per36.videolibrary.person;

import devel0per36.videolibrary.person.component.PersonalInfo;
import devel0per36.videolibrary.person.component.Role;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс для описания пользователя системы
 * @version 1.0
 */
public class User extends Person{
    private String login;    // логин пользователя
    private Role role;       // роль пользователя

    /**
     * Конструктор для создания объекта типа User
     * @param login - логин
     * @param role - роль
     * @param id - идентификатор
     * @param info - личная информация
     */
    public User(long id, String login, Role role, PersonalInfo info) {
        super(id, info);
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        return Objects.equals(this.login, user.login) && (this.role == user.role);
    }

    @Override
    public int hashCode() {
        return 31 * 1 + (login == null ? 0 : login.hashCode()) +
                (role == null ? 0 : role.hashCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "User{", "}")
                .add("login=" + login).add("role=" + role).toString();
    }
}
