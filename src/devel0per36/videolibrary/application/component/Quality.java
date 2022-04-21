package devel0per36.videolibrary.application.component;

import java.util.StringJoiner;

/**
 * Перечисление для описания качества видео
 * @version 1.0
 */
public enum Quality {
    LOW("360P"), MEDIUM("720P"), HIGH("1080P");

    private String name;    // наименование качества

    /**
     * Конструктор для объекта перечисления Quality
     * @param name - наименование жанра
     */
    Quality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Quality{", "}")
                .add("name=" + name).toString();
    }
}

