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

    @Transactional
    @Override
    public void createNewUser(User user) {
        entityManager.persist(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query =
                entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional
    @Override
    public void editUserById(long id, User user) {
        User updatedUser = getUserById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setName(user.getName());
        updatedUser.setRoles(user.getRoles());
    }

    @Transactional
    @Override
    public void updatePassword(long id, String newPassword) {
        User updatedUser = getUserById(id);
        updatedUser.setPassword(newPassword);

    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }
}