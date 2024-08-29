package com.hustlertechnology.JournalApplication.controller;

import com.hustlertechnology.JournalApplication.entity.User;
import com.hustlertechnology.JournalApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }
    @PostMapping("/health-check")
    public String addHealth(){
        return "added successfully";
    }

    @PostMapping("/create-user")
    private void addnewuser(@RequestBody User user){
        userService.saveUser(user);
    }
}
