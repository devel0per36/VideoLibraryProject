package devel0per36.videolibrary.person.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания пола человека
 * @version 1.0
 */
public enum Gender {
    MALE("Мужской"), FEMALE("Женский");

    private String name;    // наименование пола

    /**
     * Конструктор для объекта перечисления Gender
     * @param name - наименование пола
     */
    Gender(String name) {
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
