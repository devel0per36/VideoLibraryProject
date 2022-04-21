package devel0per36.videolibrary.person.component;

import devel0per36.videolibrary.general.component.Country;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс для описания личной информации
 * @version 1.0
 */
public class PersonalInfo implements Cloneable {
    private FullName name;         // полное имя
    private LocalDate dayBirth;    // дата рождения
    private Gender gender;         // пол
    private Country country;       // страна

    /**
     * Конструктор для создания объекта типа PersonalInfo
     * @param name - полное имя
     * @param dayBirth - дата рождения
     * @param gender - пол
     * @param country - страна
     */
    public PersonalInfo(FullName name, LocalDate dayBirth, Gender gender, Country country) {
        this.name = name;
        this.dayBirth = dayBirth;
        this.gender = gender;
        this.country = country;
    }

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public LocalDate getDayBirth() {
        return dayBirth;
    }

    public void setDayBirth(LocalDate dayBirth) {
        this.dayBirth = dayBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public PersonalInfo clone() throws CloneNotSupportedException {
        PersonalInfo info = (PersonalInfo) super.clone();
        info.name = name.clone();
        return info;
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
        final PersonalInfo info = (PersonalInfo) obj;
        return Objects.equals(this.name, info.name) &&
                Objects.equals(this.dayBirth, info.dayBirth) &&
                this.gender == info.gender && this.country == info.country;
    }

    @Override
    public int hashCode() {
        final int CODE =  31 * 1 + (name == null ? 0 : name.hashCode()) +
                (dayBirth == null ? 0 : dayBirth.hashCode()) +
                (gender == null ? 0 : gender.hashCode()) +
                (country == null ? 0 : country.hashCode());
        return CODE;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "PersonalInfo{", "}")
                .add("name=" + name).add("dayBirth=" + dayBirth)
                .add("gender=" + gender).add("country=" + country).toString();
    }
}
