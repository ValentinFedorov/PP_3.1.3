package com.springboot.crud.spring_boot_crud.web.dao;

import com.springboot.crud.spring_boot_crud.web.model.Role;
import com.springboot.crud.spring_boot_crud.web.model.User;
import javax.persistence.*;

import com.springboot.crud.spring_boot_crud.web.service.RoleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    public UserDaoImp(RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        if(user.getRoles() != null) {
            Iterator<Role> iterator = user.getRoles().iterator();
            while (iterator.hasNext()) {
                Role role = roleService.findRoleByRoleName(iterator.next().getRoleName());
                roleSet.add(role);
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleSet);
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user, long id) {
        Set<Role> roleSet = new HashSet<>();
        if(user.getRoles() != null) {
            Iterator<Role> iterator = user.getRoles().iterator();
            while (iterator.hasNext()) {
                Role role = roleService.findRoleByRoleName(iterator.next().getRoleName());
                roleSet.add(role);
            }
        }
        User userPreUpdate = getUser(id);
        if(!Objects.equals(userPreUpdate.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(roleSet);
        entityManager.merge(user);
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Query query = entityManager.createQuery("select u from User u where u.email = :email")
                .setParameter("email", email);
        User user = (User) query.getSingleResult();
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User with email %s not found", email));
        }
        user.getRoles().size();
        return user;
    }
}
