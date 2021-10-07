package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.models.Role;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void createNewRole(Role role) {
        List<Role> roles = getAllRoles();
        if (!roles.contains(role)) {
            if (role.getRoleName().length() < 5 || !role.getRoleName().substring(0,5).equals("ROLE_")) {
                role.setRoleName("ROLE_" + role.getRoleName());
            }
            roleDao.createNewRole(role);
        }
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public void editRoleById(int id, Role role) {
        if (role.getRoleName().length() < 5 || !role.getRoleName().substring(0,5).equals("ROLE_")) {
            role.setRoleName("ROLE_" + role.getRoleName());
        }
        roleDao.editRoleById(id, role);
    }

    @Override
    public void deleteRoleById(int id) {
        roleDao.deleteRoleById(id);
    }
}
