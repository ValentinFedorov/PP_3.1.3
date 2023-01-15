package com.springboot.crud.spring_boot_crud.web.service;

import com.springboot.crud.spring_boot_crud.web.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    List<Role> getListRoles();

    Role findRoleByRoleName(String roleName);
}
