package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {




    public UserDaoJDBCImpl() {
    }



    public void createUsersTable() {
        try {
                System.out.println("Искомая таблица не обнаружена \n" +
                        "попытка её создать");
                Util.Connection.createStatement().execute("CREATE TABLE IF NOT EXISTS `java_pre-project_1.1.4`.`TABLE_OF_USERS`  (\n" +
                        "                        `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "                         `name` VARCHAR(100) NULL,\n" +
                        "                         `lastName` VARCHAR(100) NULL,\n" +
                        "                         `age` TINYINT NULL,\n" +
                        "                         PRIMARY KEY (`id`) )");
                System.out.println("Таблица была успешно создана");
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }

    }

    public void dropUsersTable() {
        System.out.println("Начинаю процесс удаления таблицы");
        try {
                System.out.println("Таблица обнаружена, запускаю процесс удаления");
                Util.Connection.createStatement().execute("DROP TABLE IF EXISTS TABLE_OF_USERS");
                System.out.println("Таблица успешно удалена");
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println("Начинаю процесс загрузки данных");
        try {
                System.out.println("Таблица обнаружена, начинаю процесс загрузки");
                Util.Connection.prepareStatement("INSERT INTO TABLE_OF_USERS (name, lastName, age)\n"
                        + "VALUES ('" + name + "' , '" + lastName + "'," + age + ")").execute();
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public void removeUserById(long id) {
        System.out.println("Удаляю запись с указанным id");
        try {
               Util.Connection.prepareStatement("        DELETE FROM TABLE_OF_USERS\n" +
                       "        Where id = " + id).execute();
                System.out.println("Запись успешно удалена");
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public List<User> getAllUsers()  {
        System.out.println("Начинаю процесс загрузки данных их базы данных в список");
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = Util.Connection.prepareStatement("Select * FROM TABLE_OF_USERS").executeQuery();
            while(resultSet.next()) {
                users.add(new User(resultSet.getString("name"), resultSet.getString("lastName")
                        ,resultSet.getByte("age") ));
            }
            System.out.println("Данные были успешно загружены в список");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
        System.out.println("Список полученных прользователей");
        for(User u : users) {
            System.out.println(u);
        }
        System.out.println("---------------------" + "\n\n\n\n\n");

        return users;
    }




    public void cleanUsersTable() {
        System.out.println("Начинаю процемм удаления всех записей");
        try {
                Util.Connection.prepareStatement("DELETE FROM TABLE_OF_USERS").execute();
                System.out.println("Таблица была благополучно очищина");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }
}
