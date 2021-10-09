package web.dao;

import web.models.Role;
import java.util.List;

public interface RoleDao {

    void createNewRole(Role role);

    Role getRoleById(int id);

    List<Role> getAllRoles();

}