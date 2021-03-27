package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;

    public Student(Integer id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(age, student.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }
}
