package devel0per36.videolibrary.person;

import devel0per36.videolibrary.person.component.PersonalInfo;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Абстрактный класс для описания человека
 * @version 1.0
 */
public class Person implements Cloneable {
    private long id;              // идентификатор
    private PersonalInfo info;    // личная информация

    /**
     * Конструктор для создания объекта типа PersonalInfo
     * @param id - идентификатор
     * @param info - личная информация
     */
    public Person(long id, PersonalInfo info) {
        this.id = id;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalInfo getInfo() {
        return info;
    }

    public void setInfo(PersonalInfo info) {
        this.info = info;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.info = info.clone();
        return person;
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
        final Person person = (Person) obj;
        return (this.id == person.id) && Objects.equals(this.info, person.info);
    }

    @Override
    public int hashCode() {
        final int CODE = (int)(31 * 1 + id + (info == null ? 0 : info.hashCode()));
        return CODE;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Person{", "}")
                .add("id=" + id).add("info=" + info).toString();
    }
}

