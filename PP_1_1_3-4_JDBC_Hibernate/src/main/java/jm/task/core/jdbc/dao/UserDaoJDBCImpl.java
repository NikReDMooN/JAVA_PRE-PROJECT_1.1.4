package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private boolean exist = false;


    public UserDaoJDBCImpl() {
    }



    public void createUsersTable() {
        try {
            if (exist) {
                System.out.println("Искомая таблица обнаружена");
            } else {
                System.out.println("Искомая таблица не обнаружена \n" +
                        "попытка её создать");
                Util.statement.execute("CREATE TABLE `java_pre-project_1.1.4`.`TABLE_OF_USERS` (\n" +
                        "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(100) NULL,\n" +
                        "  `lastName` VARCHAR(100) NULL,\n" +
                        "  `age` TINYINT NULL,\n" +
                        "  PRIMARY KEY (`id`));\n");
                System.out.println("Таблица была успешно создана");
            }
            System.out.println("-----------------------------" + "\n\n\n\n");
            exist = true;
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }

    }

    public void dropUsersTable() {
        System.out.println("Начинаю процесс удаления таблицы");
        try {
            if (exist) {
                System.out.println("Таблица обнаружена, запускаю процесс удаления");
                Util.statement.execute("DROP TABLE TABLE_OF_USERS");
                System.out.println("Таблица успешно удалена");
                exist = false;
            } else {
                System.out.println("Таблица не обнаружена, удаление не возможно");
            }
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println("Начинаю процесс загрузки данных");
        try {

            if (exist) {
                System.out.println("Таблица обнаружена, начинаю процесс загрузки");
                Util.connection.prepareStatement("INSERT INTO TABLE_OF_USERS (name, lastName, age)\n"
                        + "VALUES ('" + name + "' , '" + lastName + "'," + age + ")").execute();
            } else {
                System.out.println("Таблица не обнаружена, загрузка данных невозможна \n"
                + "Пожалуйста создадите сначала таблицу");
            }
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public void removeUserById(long id) {
        System.out.println("Удаляю запись с указанным id");
        try {
           if (exist) {
               Util.connection.prepareStatement("        DELETE FROM TABLE_OF_USERS\n" +
                       "        Where id = " + id).execute();
                System.out.println("Запись успешно удалена");
           } else {
               System.out.println("Таблица не была обнаружена");

           }
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public List<User> getAllUsers()  {
        System.out.println("Начинаю процесс загрузки данных их базы данных в список");
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = Util.connection.prepareStatement("Select * FROM TABLE_OF_USERS").executeQuery();
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



    public void createConnection() {
        try {
            PreparedStatement preparedStatement = Util.connection.prepareStatement("SELECT count(*) "
                    + "FROM information_schema.tables "
                    + "WHERE table_name = ?"
                    + "LIMIT 1;");
            preparedStatement.setString(1, "TABLE_OF_USERS");

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) != 0) {
                exist = true;
            } else {
                exist = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        System.out.println("Начинаю процемм удаления всех записей");
        try {
            if (exist) {
                Util.connection.prepareStatement("DELETE FROM TABLE_OF_USERS").execute();
                System.out.println("Таблица была благополучно очищина");
            } else {
                System.out.println("Таблицы не существует");
                System.out.println("-----------------------------" + "\n\n\n\n");
            }
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }
}
