package database;

import models.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataBaseLogicTest {

    static DataBaseLogic dataBaseLogic;
    static DataBaseConnection dataBaseConnection;

    @BeforeAll
    static void init() {
        dataBaseConnection = new DataBaseConnection();
        dataBaseLogic = new DataBaseLogic(dataBaseConnection);
    }


    @Test
    void addStudent() {
        final Integer integer = Integer.valueOf(-1);
        final Integer ans =  dataBaseLogic.addStudent(new Student(null,"Test","Add",228));
        assertThat(integer).isNotEqualTo(ans);
        if (ans != -1)dataBaseLogic.deleteStudent(ans);

    }



    @Test
    void deleteStudent() {
        final Integer testStudent  =  dataBaseLogic.addStudent(new Student(null,"Test","Delete",666));
        final boolean ans  = dataBaseLogic.deleteStudent(testStudent);
        assertTrue(ans);
    }

    @Test
    void updateStudent() {
        final Integer testStudent  =  dataBaseLogic.addStudent(new Student(null,"Test","Update",443));
        final Student studentFortUpdate = new Student(testStudent,"NEWTEST","UPDATE",77);
        final boolean ans = dataBaseLogic.updateStudent(studentFortUpdate);
        assertTrue(ans);
        dataBaseLogic.deleteStudent(testStudent);
    }

    @Test
    void sortStudents() {
        ArrayList<Student> students = dataBaseLogic.getStudents();
        for (Student student : students) {
            dataBaseLogic.deleteStudent(student.getId());
        }
        students = new ArrayList<>();
        students.add(new Student(null, "test4", "Test3", 400));
        students.add(new Student(null, "test4", "Test1", 23));
        students.add(new Student(null, "test4", "Test2", 123));

        for (Student student : students) {
            student.setId(dataBaseLogic.addStudent(student));
        }
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }.thenComparing(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        }).thenComparing(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        }));
        final ArrayList<Student> ans = dataBaseLogic.sortStudents();
        for (int i = 0 ; i<students.size();i++) {
            System.out.println(students.get(i));
            System.out.println(ans.get(i));
            assertEquals(students.get(i),ans.get(i));
        }
    }

    @Test
    void getStudents() {

        ArrayList<Student> students = dataBaseLogic.getStudents();
        for (Student student : students) {
            dataBaseLogic.deleteStudent(student.getId());
        }
        students = new ArrayList<>();
        students.add(new Student(null, "test", "Test", 12));
        students.add(new Student(null, "test1", "Test1", 23));
        students.add(new Student(null, "test2", "Test2", 123));
        for (Student student : students) {
            student.setId(dataBaseLogic.addStudent(student));
        }

        assertEquals(students, dataBaseLogic.getStudents());
    }
}