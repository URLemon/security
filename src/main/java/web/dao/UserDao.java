package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    User getUserByLogin(String login);
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    User findById(long id);
    void deleteById(long id);
}
