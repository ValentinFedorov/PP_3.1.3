package com.springboot.crud.spring_boot_crud.web.repositories;

import com.springboot.crud.spring_boot_crud.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
