package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void createNewRole(Role role) {
        entityManager.persist(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleById(int id) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public Role getRoleByName(String roleName) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class);
        query.setParameter("roleName", roleName);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Transactional
    @Override
    public void editRoleById(int id, Role role) {
        Role updatedRole = getRoleById(id);
        updatedRole.setRoleName(role.getRoleName());
        updatedRole.setDescription(role.getDescription());
    }

    @Transactional
    @Override
    public void deleteRoleById(int id) {
        entityManager.remove(getRoleById(id));
    }
}
