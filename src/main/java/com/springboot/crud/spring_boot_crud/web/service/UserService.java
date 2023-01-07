package com.springboot.crud.spring_boot_crud.web.service;



import com.springboot.crud.spring_boot_crud.web.model.User;
import com.springboot.crud.spring_boot_crud.web.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    void add(User user);
    public List<User> getAllUsers();

    public void deleteUser(long id);

    public User getUserById(long id);

    public void updateUser(long id, User updatedUser);
    public User findByUsername(String username);

}
