package jm.task.core.jdbc.util;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String URL = "jdbc:mysql://localhost:3306/java_pre-project_1.1.4";
    private static String user = "root";
    private static String password = "Weskerandrik1!";

    private Util(){
    }



    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL
                    , user
                    , password);
        } catch (SQLException e) {
            System.out.println("Во время работы возникла ошибка " + e);
        }
        return connection;
    }

    // реализуйте настройку соеденения с БД
}
