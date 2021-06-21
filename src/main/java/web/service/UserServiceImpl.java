package web.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDao dao;

    public UserServiceImpl(@Qualifier("userDaoImpl") UserDao dao){
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    @Transactional
    public User findById(long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        dao.deleteById(id);
    }
}
