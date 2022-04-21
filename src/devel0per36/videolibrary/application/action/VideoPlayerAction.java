package devel0per36.videolibrary.application.action;

import devel0per36.videolibrary.application.component.Quality;

/**
 * Интерфейс для описания абстрактных методов для работы с фильмом в видеоплеере
 * @version 1.0
 */
public interface VideoPlayerAction {
    /**
     * Изменение качества видео
     * @param quality - качество видео
     * @return возвращает логический ответ о том, изменено ли качество видео
     */
    boolean changeVideoQuality(Quality quality);

    /**
     * Включение звука
     */
    void turnOnSound();

    /**
     * Выключение звука
     */
    void turnOffSound();
}
