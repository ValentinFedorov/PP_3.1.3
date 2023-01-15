package com.springboot.crud.spring_boot_crud.web.service;

import com.springboot.crud.spring_boot_crud.web.dao.RoleDao;
import com.springboot.crud.spring_boot_crud.web.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements  RoleService{

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void createRole(Role role) {
        roleDao.createRole(role);
    }

    @Override
    public List<Role> getListRoles() {
        return roleDao.getListRoles();
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleDao.findRoleByRoleName(roleName);
    }
}
