package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl userDaoJDBC;

    public void setUserDaoJDBC(UserDaoJDBCImpl userDaoJDBC) {
        this.userDaoJDBC = userDaoJDBC;
    }

    public UserServiceImpl() {
        this.userDaoJDBC = new UserDaoJDBCImpl();
        this.userDaoJDBC.createConnection();
    }

    public UserDaoJDBCImpl getUserDaoJDBC() {
        return userDaoJDBC;
    }

    public void setStatement(Statement statement) {
        this.userDaoJDBC.setStatement(statement);
    }

    public void setConnection(Connection connection) {
        this.userDaoJDBC.setConnection(connection);
    }

    public void createUsersTable() {
        this.userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        this.userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        this.userDaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        this.userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return this.userDaoJDBC.getAllUsers();
    }

    public void isExist(){
        this.userDaoJDBC.isExist();
    }

    public void cleanUsersTable() {
        this.userDaoJDBC.cleanUsersTable();
    }
}
