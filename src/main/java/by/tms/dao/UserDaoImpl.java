package by.tms.dao;

import by.tms.entity.Address;
import by.tms.entity.User;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
    }

    public List<User> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from User", User.class)
                .getResultList();
    }


    public List<User> findAllByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from User where name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    public User findByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from User where username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public User findById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }

    public void updateName(String name, long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, id);
        user.setName(name); //-_-
    }

    public void updateUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        User userFromDB = currentSession
                .createQuery("from User where username = :username", User.class)
                .setParameter("username", user.getUsername())
                .getSingleResult();
        userFromDB.setName(user.getName());
        userFromDB.setPassword(user.getPassword());
        Address address = userFromDB.getAddress();
        address.setStreet(user.getAddress().getStreet());
        address.setHome(user.getAddress().getHome());
    }

    public boolean contains(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long isExistIndicator = (Long) currentSession
                .createQuery("select count(*) from User where username = :username")
                .setParameter("username", username)
                .uniqueResult();
        return isExistIndicator > 0L;
    }

    public boolean contains(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long isExistIndicator = currentSession
                .createQuery("select count(*) from User where id = :id", Long.class)
                .setParameter("id", id)
                .uniqueResult();
        return isExistIndicator > 0L;
    }

    public void delete(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.get(User.class, id);
        currentSession.delete(user);
    }

    public void delete(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession
                .createQuery("delete User where username like :username")
                .setParameter("username", username)
                .executeUpdate();
    }

}
