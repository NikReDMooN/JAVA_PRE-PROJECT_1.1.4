package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static String URL = "jdbc:mysql://localhost:3306/java_pre-project_1.1.4";
    private static String user = "root";
    private static String password = "Weskerandrik1!";

    public static Connection Connection;

    private Util(){
    }



    public static Connection buildConnection(){
        try {
            Connection = DriverManager.getConnection(URL
                    , user
                    , password);
        } catch (SQLException e) {
            System.out.println("Во время работы возникла ошибка " + e);
        }
        return Connection;
    }

    // реализуйте настройку соеденения с БД
}
