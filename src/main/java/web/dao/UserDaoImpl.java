package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u where u.login=:login", User.class);
        query.setParameter("login", login);
        Optional<User> u = query.getResultList().stream().findAny();
        return u.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> allUsers = entityManager.createQuery("from User", User.class);
        return allUsers.getResultList();
    }

    @Override
    public void saveUser(User user) {
        //entityManager.persist(user); почему не работает?
        entityManager.merge(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

//    @Override
//    public User findById(int id) {
//        return entityManager.find(User.class, id);
//    }

    @Override
    public User findById(long id) {
        TypedQuery<User> user = entityManager.createQuery("select u from User u where u.id=:id", User.class);
        user.setParameter("id", id);
        return user.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void deleteById(long id) {
        Query q =entityManager.createQuery("delete from User u where u.id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

}
