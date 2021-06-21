package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    User findById(long id);
    void deleteById(long id);
}
