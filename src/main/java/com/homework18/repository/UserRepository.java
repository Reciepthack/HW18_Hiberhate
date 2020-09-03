package com.homework18.repository;


import com.homework18.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void createUser(User user) {
        getCurrentSession().persist(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        return getCurrentSession().createQuery("Select u From User u").list();
    }

    @Transactional
    public User getUserById(Long userId) {
        return getCurrentSession().get(User.class, userId);
    }

    @Transactional
    public void deleteUser(User user) {
        getCurrentSession().delete(user);
    }

    @Transactional
    public void editUser(User user) {
        getCurrentSession().update(user);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
