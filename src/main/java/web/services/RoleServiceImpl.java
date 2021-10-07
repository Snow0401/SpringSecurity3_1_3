package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.models.Role;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void createNewRole(Role role) {
        roleDao.createNewRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    /*@Transactional
    @Override
    public void editRoleById(int id, Role role) {
        if (role.getRoleName().length() < 5 || !role.getRoleName().substring(0,5).equals("ROLE_")) {
            role.setRoleName("ROLE_" + role.getRoleName());
        }
        roleDao.editRoleById(id, role);
    }*/

    /*@Transactional
    @Override
    public void deleteRoleById(int id) {
        roleDao.deleteRoleById(id);
    }*/
}
