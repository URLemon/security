package web.dao;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Entity;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String nameRole) {
        TypedQuery<Role> role = entityManager.createQuery("SELECT r From Role r WHERE r.role=:name", Role.class);
        role.setParameter("name", nameRole);
        return role.getSingleResult();
    }
}
