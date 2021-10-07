package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void createNewUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query =
                entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void editUserById(long id, User user) {
        entityManager.merge(user);
    }


    @Override
    public void updatePassword(long id, String newPassword) {
        User updatedUser = getUserById(id);
        updatedUser.setPassword(newPassword);

    }

    @Override
    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }
}