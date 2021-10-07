package web.dao;

import web.models.User;
import java.util.List;

public interface UserDao {
    void createNewUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void editUserById(long id, User user);

    void deleteUserById(long id);

    User getUserByName(String name);

}
