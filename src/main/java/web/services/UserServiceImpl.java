package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.models.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createNewUser(User user) {
        if (getUserByName(user.getName()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.createNewUser(user);
        }
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void editUserById(long id, User user) {
        userDao.editUserById(id, user);
    }

    @Override
    public void updatePassword(long id, String newPassword) {
        userDao.updatePassword(id, passwordEncoder.encode(newPassword));
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
