package devel0per36.videolibrary.server.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания статуса сервера
 * @version 1.0
 */
public enum Status {
    ACTIVE("Активен"), UNAVAILABLE("Недоступен");

    private String name;    // наименование статуса

    /**
     * Конструктор для объекта перечисления Status
     * @param name - наименование жанра
     */
    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Status{", "}")
                .add("name=" + name).toString();
    }
}
