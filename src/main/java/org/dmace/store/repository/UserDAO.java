package org.dmace.store.repository;

import org.dmace.store.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Basic repository layer with CRUD operations
 *
 */
@Repository
@Transactional
public class UserDAO {

    @PersistenceContext
    private EntityManager em;


    public void create(User user) {
        em.persist(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        if(em.contains(user))
            em.remove(user);
        else
            em.remove(em.merge(user));
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return em.createQuery("SELECT u from User u").getResultList();
    }

    public User getByEmailAndPwd(String email, String password) {
        return new User();
    }

    public User getById(long id) {
        return em.find(User.class, id);
    }

    public int getNumPostsByUser(User user) {
        return 0;
    }
}
