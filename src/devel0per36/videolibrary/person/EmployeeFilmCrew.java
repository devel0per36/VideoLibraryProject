package devel0per36.videolibrary.person;

import devel0per36.videolibrary.person.component.PersonalInfo;
import devel0per36.videolibrary.person.component.Position;

import java.util.StringJoiner;

/**
 * Класс для описания работника съёмочной группы
 * @version 1.0
 */
public class EmployeeFilmCrew extends Person {
    private Position position;    // должность

    /**
     * Конструктор для создания объекта типа FilmCrewWorker
     * @param id - идентификатор
     * @param info - личная информация
     * @param position - должность
     */
    public EmployeeFilmCrew(long id, PersonalInfo info, Position position) {
        super(id, info);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public EmployeeFilmCrew clone() throws CloneNotSupportedException {
        EmployeeFilmCrew employee = (EmployeeFilmCrew) super.clone();
        return employee;
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
        final EmployeeFilmCrew employee = (EmployeeFilmCrew) obj;
        return super.equals(employee) && this.position == employee.position;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (position == null ? 0 : position.hashCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "FilmCrewWorker{", "}")
                .add(super.toString()).add("position=" + position).toString();
    }
}
