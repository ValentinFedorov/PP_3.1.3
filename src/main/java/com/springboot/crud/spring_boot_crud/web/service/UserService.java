package com.springboot.crud.spring_boot_crud.web.service;



import com.springboot.crud.spring_boot_crud.web.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    void deleteUser(long id);

    void updateUser(User user, long id);

    List<User> getListUsers();

    User getUser(long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
