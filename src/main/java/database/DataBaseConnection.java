package database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
public class DataBaseConnection {
    private static BasicDataSource ds = new BasicDataSource();;
    private final static String USER_NAME = "postgres";
    private final static String PASSWORD = "1234";
    private final static String DB_URL = "jdbc:postgresql://192.168.1.233:5432/java_intensive" ;
    static {
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(DB_URL);
        ds.setUsername(USER_NAME);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
    /*public DataBaseConnection() {
        ds = new BasicDataSource();
        configureDS();
    }

    private void configureDS(){

    }*/

    public static Connection getDbConnection() throws SQLException {
        return ds.getConnection();

    }

    public static void  close() throws SQLException {
        ds.close();
    }

}
