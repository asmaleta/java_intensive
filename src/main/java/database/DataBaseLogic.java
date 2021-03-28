package database;

import models.Student;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseLogic {


    public DataBaseLogic() {

    }

    public Integer addStudent(Student student) {
        try (Connection connection = DataBaseConnection.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.ADD_STUDENT.QUERY)) {
            int pointer = 0;
            preparedStatement.setString(++pointer, student.getName());
            preparedStatement.setString(++pointer, student.getSurname());
            preparedStatement.setInt(++pointer, student.getAge());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            else return -1;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    public boolean deleteStudent(int id) {
        try (Connection connection = DataBaseConnection.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.DELETE_STUDENT_BY_ID.QUERY)) {
            int pointer = 0;
            preparedStatement.setInt(++pointer, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudent(Student student) {
        try (Connection connection = DataBaseConnection.getDbConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.UPDATE_STUDENT_BY_ID.QUERY)) {
            int pointer = 0;
            preparedStatement.setString(++pointer, student.getName());
            preparedStatement.setString(++pointer, student.getSurname());
            preparedStatement.setInt(++pointer, student.getAge());
            preparedStatement.setInt(++pointer, student.getId());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Student> sortStudents(){
        ArrayList<Student> students = null;
        try (Connection connection = DataBaseConnection.getDbConnection();
             Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(StudentsSQL.SORT_STUDENTS.QUERY);
            students = new ArrayList<>();
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")));
            }
        }catch (SQLException e){
            e.printStackTrace();
            students = null;
        }finally {
            return students;
        }

    }
    public Student getStudentById(Integer id){
        Student student = null;
        try (Connection connection = DataBaseConnection.getDbConnection();
             PreparedStatement statement = connection.prepareStatement(StudentsSQL.GET_STUDENT_BY_ID.QUERY)){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            student = null;
        }finally {
            return student;
        }

    }
    public ArrayList<Student> getStudents(){
        ArrayList<Student> students = null;
        try (Connection connection =DataBaseConnection.getDbConnection();
                Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(StudentsSQL.GET_STUDENTS.QUERY);
            students = new ArrayList<>();
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")));
            }
        }catch (SQLException e){
            e.printStackTrace();
            students = null;
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
        GET_STUDENT_BY_ID("SELECT * FROM students where id = ? "),

        SORT_STUDENTS("SELECT * FROM students ORDER BY name, surname, age");



        String QUERY;
        StudentsSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
