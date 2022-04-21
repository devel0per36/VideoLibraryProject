package devel0per36.videolibrary.person.component;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс для описания полного имени
 * @version 1.0
 */
public class FullName implements Cloneable {
    private String firstName;    // имя
    private String lastName;     // фамилия

    /**
     * Конструктор для создания объекта типа FullName
     * @param firstName - имя
     * @param lastName - фамилия
     */
    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public FullName clone() throws CloneNotSupportedException {
        FullName name = (FullName) super.clone();
        return name;
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
        final FullName name = (FullName) obj;
        return Objects.equals(this.firstName, name.firstName) &&
                Objects.equals(this.lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        final int CODE = 31 * 1 + (firstName == null ? 0 : firstName.hashCode() +
                (lastName == null ? 0 : lastName.hashCode()));
        return CODE;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "FullName{", "}")
                .add("firstName=" + firstName).add("lastName=" + lastName).toString();
    }
}
