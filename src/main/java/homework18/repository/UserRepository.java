package homework18.repository;

import homework18.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public boolean checkExistsById(Long id) {
        return !getCurrentSession().createQuery("Select u.id FROM User u WHERE u.id = :id", Long.class)
                .setParameter("id", id)
                .getResultList().isEmpty();
    }

    @Transactional
    public void createUser(User user) {
        getCurrentSession().persist(user);
    }

    @Transactional
    public User findUserByIdJoinFetch(Long id) {
        try {
            return getCurrentSession()
                    .createQuery("Select u FROM User u left join fetch u.books WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("ТУТ ПРОБЛЕМА");
           return new User();
        }

    }

    @Transactional
    public User findUserById(Long id) {
        try {
            return getCurrentSession()
                    .createQuery("Select u FROM User u WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
          return new User();
        }
    }

    @Transactional
    public List<User> findAllUsers() {
        try {
            return getCurrentSession()
                    .createQuery("Select u FROM User u", User.class)
                    .getResultList();
        } catch (NoResultException nre) {
        return new ArrayList<>();
        }
    }

    @Transactional
    public void update(User user) {
        getCurrentSession().update(user);
    }

    @Transactional
    public int deleteById(Long id) {
        try {
            return getCurrentSession()
                    .createQuery("DELETE FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (NoResultException nre) {
           return 0;
        }
    }
}