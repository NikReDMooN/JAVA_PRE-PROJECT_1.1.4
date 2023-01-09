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

    public static Connection connection;
    public static Statement statement;

    private Util(){
    }



    public static Connection buildConnection(UserDao userDao){
        try {
            connection = DriverManager.getConnection(URL
                    , user
                    , password);
            statement = connection.createStatement();
            if (userDao instanceof UserDaoJDBCImpl) {
                UserDaoJDBCImpl userDaoJDBC = (UserDaoJDBCImpl)userDao;
                userDaoJDBC.createConnection();
            }
        } catch (SQLException e) {
            System.out.println("Во время работы возникла ошибка " + e);
        }
        return connection;
    }

    // реализуйте настройку соеденения с БД
}
