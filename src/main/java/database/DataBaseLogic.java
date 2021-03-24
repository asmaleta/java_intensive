package database;

import models.Student;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class DataBaseLogic {
    private volatile DataBaseConnection dbs;

    public DataBaseLogic(DataBaseConnection dataBaseConnection) {
        this.dbs = dataBaseConnection;
    }
    public Integer addStudent(Student student) {
        try (Connection connection = dbs.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.ADD_STUDENT.QUERY)) {
            int pointer = 0;
            preparedStatement.setString(++pointer, student.getName());
            preparedStatement.setString(++pointer, student.getSurname());
            preparedStatement.setInt(++pointer, student.getAge());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getInt(1);
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    public void deleteStudent(int id) {
        try (Connection connection = dbs.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.DELETE_STUDENT_BY_ID.QUERY)) {
            int pointer = 0;
            preparedStatement.setInt(++pointer, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try (Connection connection = dbs.getDbConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.UPDATE_STUDENT_BY_ID.QUERY)) {
            int pointer = 0;
            preparedStatement.setString(++pointer, student.getName());
            preparedStatement.setString(++pointer, student.getSurname());
            preparedStatement.setInt(++pointer, student.getAge());
            preparedStatement.setInt(++pointer, student.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public LinkedList<Student> sortStudents(){
        LinkedList<Student> students = new LinkedList<>();
        try (Connection connection = dbs.getDbConnection();
             Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(StudentsSQL.SORT_STUDENTS_BY_AGE.QUERY);
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return students;
        }

    }
    public LinkedList<Student> getStudents(){
        LinkedList<Student> students = new LinkedList<>();
        try (Connection connection = dbs.getDbConnection();
                Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(StudentsSQL.GET_STUDENTS.QUERY);
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return students;
        }

    }

    private enum StudentsSQL {

        GET_STUDENTS("SELECT * FROM students"),

        ADD_STUDENT("" +
                "INSERT INTO students(name, surname, age) VALUES(?, ?, ?) RETURNING id"),


        UPDATE_STUDENT_BY_ID("" +
                "UPDATE students SET name = ?, surname = ? , age = ? \n" +
                "WHERE students.id = ?"),

        DELETE_STUDENT_BY_ID("DELETE FROM students where id = ? "),

        SORT_STUDENTS_BY_AGE("SELECT * FROM students ORDER BY age");



        String QUERY;
        StudentsSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
