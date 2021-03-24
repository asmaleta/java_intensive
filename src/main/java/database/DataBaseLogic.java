package database;

import models.Student;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class DataBaseLogic {
    private Connection connection;

    public DataBaseLogic(DataBaseConnection dataBaseConnection) {
        this.connection = dataBaseConnection.getDbConnection();
    }


    private void updateStudent(Student student, int studentID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.UPDATE_STUDENT_BY_ID.QUERY)) {
            int pointer = 0;
            preparedStatement.setString(++pointer, student.getName());
            preparedStatement.setString(++pointer, student.getSurname());
            preparedStatement.setInt(++pointer, student.getAge());
            preparedStatement.setInt(++pointer, studentID);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public LinkedList<Student> getStudents(){
        LinkedList<Student> students = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(StudentsSQL.GET_STUDENTS.QUERY);
            ResultSet rs = preparedStatement.executeQuery();
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

    public static enum StudentsSQL {

        GET_STUDENTS("SELECT * FROM students"),

        /**
         * ADD commands
         */
        ADD_DRIVER("" +
                "INSERT INTO drivers(login, password) VALUES(?, ?) RETURNING id"),

        ADD_DRIVER_ROUTE_COMMUNICATION("" +
                "INSERT INTO drivers_routes VALUES (?, ?)"),

        /**
         * UPDATE commands
         */
        UPDATE_STUDENT_BY_ID("" +
                "UPDATE students SET name = ?, surname = ? , age = ? \n" +
                "WHERE students.id = ?"),
        /**
         * DELETE commands
         */
        REMOVE_DRIVER("DELETE FROM drivers where login = ? AND password = ? RETURNING id");
        String QUERY;

        StudentsSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
