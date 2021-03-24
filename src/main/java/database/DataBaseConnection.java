package database;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
@Getter
public class DataBaseConnection {

    private final String USER_NAME = "postgres";
    private final String PASSWORD = "1234";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/java_intensive" ;
    private Connection dbConnection;

    public DataBaseConnection() {
        this.dbConnection = createConnection();
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при загузке настроек дб ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
          return connection;
        }
    }
}
