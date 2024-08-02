package com.hustlertechnology.JournalApplication.controller;


import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import com.hustlertechnology.JournalApplication.entity.User;
import com.hustlertechnology.JournalApplication.repository.UserRepository;
import com.hustlertechnology.JournalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/id/{id}")
    private Optional<User> getUserByID(@PathVariable ObjectId id){
        return userService.getById(id);
    }
    @PostMapping
    private void addnewuser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @DeleteMapping
    private ResponseEntity<?> deleteUserById(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
           userInDb.setUserName(user.getUserName());
           userInDb.setPassword(user.getPassword());
           userService.saveNewUser(userInDb);
           return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
