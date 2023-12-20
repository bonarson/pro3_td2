package dataBaseConfig;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class connect_to_db {
    public Connection conect_db(String dbName, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
            if (conn != null) {
                System.out.println("connection done");
            } else {
                System.out.println("connection error");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
