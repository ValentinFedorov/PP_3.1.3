package com.springboot.crud.spring_boot_crud.web.dao;

import com.springboot.crud.spring_boot_crud.web.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements  RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getListRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        Query query = entityManager.createQuery("select r from Role r where r.roleName = :roleName")
                .setParameter("roleName", roleName);
        return  (Role) query.getSingleResult();
    }
}
