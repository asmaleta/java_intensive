package database;

import lombok.Getter;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class DataBaseConnection {
    private PGSimpleDataSource ds;


    private final String USER_NAME = "postgres";
    private final String PASSWORD = "1234";
    private final String DB_URL = "jdbc:postgresql://10.0.2.15:5432/java_intensive" ;

    public DataBaseConnection() {
        ds = new PGSimpleDataSource();
    }

    private void configureDS(){
        ds.setURL(DB_URL);
        ds.setUser(USER_NAME);
        ds.setPassword(PASSWORD);
    }

    public Connection getDbConnection() {
        configureDS();
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при загузке настроек дб ");
        }finally {
            return connection;
        }
    }
}
