package com.springboot.crud.spring_boot_crud.web.repositories;

import com.springboot.crud.spring_boot_crud.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
