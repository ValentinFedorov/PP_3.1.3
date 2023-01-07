package com.springboot.crud.spring_boot_crud.web.controller;

import com.springboot.crud.spring_boot_crud.web.model.User;
import com.springboot.crud.spring_boot_crud.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String User(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User) userService.findByUsername(auth.getName());
//        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user1);
        return "user";
    }
}
