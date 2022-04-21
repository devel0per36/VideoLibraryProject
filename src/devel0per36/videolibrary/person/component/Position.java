package devel0per36.videolibrary.person.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания должности
 * @version 1.0
 */
public enum Position {
    ACTOR("Актёр"), DIRECTOR("Режиссёр"), OPERATOR("Оператор");

    private String name;    // наименование должности

    /**
     * Конструктор для объекта перечисления Position
     * @param name - наименование должности
     */
    Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Gender{", "}")
                .add("name=" + name).toString();
    }
}
