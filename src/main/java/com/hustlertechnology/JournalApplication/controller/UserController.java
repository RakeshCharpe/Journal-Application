package com.hustlertechnology.JournalApplication.controller;


import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import com.hustlertechnology.JournalApplication.entity.User;
import com.hustlertechnology.JournalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
       return userService.getEntryList();
    }
    @GetMapping("/id/{id}")
    private Optional<User> getUserByID(@PathVariable ObjectId id){
        return userService.getById(id);
    }
    @PostMapping
    private void addnewuser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @DeleteMapping("/id/{id}")
    private ResponseEntity<?> deleteUserById(@PathVariable ObjectId id){
        userService.deleteEntry(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    private ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
           userInDb.setUserName(user.getUserName());
           userInDb.setPassword(user.getPassword());
           userService.saveEntry(userInDb);
           return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
