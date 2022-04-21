package devel0per36.videolibrary.person.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания роли пользователя в системе
 * @version 1.0
 */
public enum Role {
    USER("Пользователь"), MODERATOR("Модератор"), ADMIN("Администратор");

    private String name;    // наименование роли

    /**
     * Конструктор для объекта перечисления Role
     * @param name - наименование жанра
     */
    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Role{", "}")
                .add("name=" + name).toString();
    }
}
