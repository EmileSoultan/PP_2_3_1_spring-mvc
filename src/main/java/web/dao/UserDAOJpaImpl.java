package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserDAOJpaImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User showUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(long id, User user) {
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setSurname(user.getSurname());
        userToBeUpdated.setEmail(user.getEmail());
    }

    @Transactional
    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}

