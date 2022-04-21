package devel0per36.videolibrary.general.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания стран
 * @version 1.0
 */
public enum Country {
    RUSSIA("643", "Россия"), USA("840", "США"), GERMANY("567", "Германия"),
    JAPAN("392", "Япония"), CHINA("156", "Китай"), FRANCE("250", "Франция");

    private final String code;    // код страны
    private final String name;    // наименование страны

    /**
     * Конструктор для объекта перечисления Country
     * @param code - код страны
     * @param name  - наименование страны
     */
    Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Country{", "}")
                .add("code=" + code).add("name=" + name).toString();
    }
}
