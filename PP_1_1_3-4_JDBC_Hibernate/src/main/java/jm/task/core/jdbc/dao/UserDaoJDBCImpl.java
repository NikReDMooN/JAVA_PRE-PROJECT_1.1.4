package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    private Statement statement;

    private boolean exist = false;

    public void isExist(){
        System.out.println("Начинаю проверку на наличие таблицы");
        System.out.println("Проверяяю...");
        DatabaseMetaData md = null;
        try {
            md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "TABLE_OF_USERS", null);
            if (rs.next()) {
                System.out.println("Таблица найдена");
                exist = true;
            } else {
                System.out.println("Таблица не найдена");
            }
            System.out.println("-----------------------------" + "\n\n\n\n");
        } catch (SQLException e) {
            System.out.println("обнаружена ошибка " + e);
        }
    }

    public UserDaoJDBCImpl() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void createUsersTable() {
        DatabaseMetaData md = null;
        try {
            if (exist) {
                System.out.println("Искомая таблица обнаружена");
            } else {
                System.out.println("Искомая таблица не обнаружена \n" +
                        "попытка её создать");
                statement.execute("CREATE TABLE `java_pre-project_1.1.4`.`TABLE_OF_USERS` (\n" +
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
        DatabaseMetaData md = null;
        try {
            if (exist) {
                System.out.println("Таблица обнаружена, запускаю процесс удаления");
                statement.execute("DROP TABLE TABLE_OF_USERS");
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
                statement.execute("INSERT INTO TABLE_OF_USERS (name, lastName, age)\n"
                        + "VALUES ('" + name + "' , '" + lastName + "'," + age + ")");
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
               statement.execute("        DELETE FROM TABLE_OF_USERS\n" +
                       "        Where id = " + id);
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
            ResultSet resultSet = statement.executeQuery("Select * FROM TABLE_OF_USERS");
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

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void createConnection() {
        this.connection = Util.getConnection();
        try {
            this.statement = connection.createStatement();
            isExist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        isExist();
    }

    public void cleanUsersTable() {
        System.out.println("Начинаю процемм удаления всех записей");
        try {
            if (exist) {
                statement.execute("DELETE FROM TABLE_OF_USERS");
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
