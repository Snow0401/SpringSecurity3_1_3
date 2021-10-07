package web.services;

import web.models.Role;
import java.util.List;

public interface RoleService {
    void createNewRole(Role role);

    Role getRoleById(int id);

    Role getRoleByName(String roleName);

    List<Role> getAllRoles();

    void editRoleById(int id, Role role);

    void deleteRoleById(int id);
}

