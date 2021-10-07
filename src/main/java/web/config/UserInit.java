package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserInit {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    private void loadTestUsers() {


        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        adminRole.setDescription("Allow user's and roles administration");
        roleService.createNewRole(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        userRole.setDescription("Simply user");
        roleService.createNewRole(userRole);

        Set<Role> roles = new HashSet<>();
        User userAdmin = new User("Artem", "Gorban", (byte) 31, "admin", "admin");
        roles.add(userRole);
        roles.add(adminRole);
        userAdmin.setRoles(roles);
        userService.createNewUser(userAdmin);

        roles.clear();
        User user1 = new User("Anastasiya", "Stepnova", (byte) 24, "user", "user");
        roles.add(userRole);
        user1.setRoles(roles);
        userService.createNewUser(user1);
    }
}
