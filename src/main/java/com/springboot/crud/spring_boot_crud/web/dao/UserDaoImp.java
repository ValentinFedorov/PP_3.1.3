package com.springboot.crud.spring_boot_crud.web.dao;

import com.springboot.crud.spring_boot_crud.web.model.User;
import javax.persistence.*;

import com.springboot.crud.spring_boot_crud.web.repositories.RoleRepository;
import com.springboot.crud.spring_boot_crud.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entityManager.persist(user);

    }

    public List<User> getAllUsers() {
          return userRepository.findAll();
    }

    public User getUserById(long id) {
        return  entityManager.find(User.class, id);
    }

    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    public void updateUser(long id, User updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        entityManager.merge(user);
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
