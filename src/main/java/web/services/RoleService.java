package web.services;

import web.models.Role;
import java.util.List;

public interface RoleService {

    void createNewRole(Role role);

    Role getRoleById(int id);

    List<Role> getAllRoles();

}

