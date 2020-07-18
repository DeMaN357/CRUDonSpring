package com.kirill.man.web.dao.RoleDao;

import com.kirill.man.web.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRole(String role) {
        return entityManager.createQuery("select role1 from Role role1 where role1.role = : role", Role.class)
                .setParameter("role", role).getSingleResult();
    }
}
