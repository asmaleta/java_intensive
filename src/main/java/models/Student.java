package models;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}
