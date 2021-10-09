package web.services;

import web.models.User;
import java.util.List;

public interface UserService {

    void createNewUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void editUserById(User user);

    void deleteUserById(long id);

    User getUserByName(String name);
}
