package web.dao;

import web.models.Role;

import java.util.List;

public interface RoleDao {
    void createNewRole(Role role);

    Role getRoleById(int id);

    Role getRoleByName(String roleName);

    List<Role> getAllRoles();

    void editRoleById(int id, Role role);

    void deleteRoleById(int id);
}