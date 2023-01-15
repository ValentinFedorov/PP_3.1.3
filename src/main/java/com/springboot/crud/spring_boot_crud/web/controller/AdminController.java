package com.springboot.crud.spring_boot_crud.web.controller;

import com.springboot.crud.spring_boot_crud.web.model.Role;
import com.springboot.crud.spring_boot_crud.web.model.User;
import com.springboot.crud.spring_boot_crud.web.service.RoleService;
import com.springboot.crud.spring_boot_crud.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {


    private UserService userService;

    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers (Model model) {
        List<User> allUsers = userService.getListUsers();
        model.addAttribute("allUsers", allUsers);

        return "all-users";
    }

    @RequestMapping(value = "/addUsers")
    public String addUsers (Model model) {
        User user = new User();
        List<Role> roles = roleService.getListRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "addUser";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/changeUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        List<Role> roles = roleService.getListRoles();
        model.addAttribute("allRoles", roles);
        model.addAttribute(userService.getUser(id));
        return "updateUsers";
    }

    @PatchMapping("/changeUser/{id}")
    public String editUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        userService.updateUser(user, id);
        return "redirect:/admin";
    }
}
