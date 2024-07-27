package com.hustlertechnology.JournalApplication.service;

import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import com.hustlertechnology.JournalApplication.entity.User;
import com.hustlertechnology.JournalApplication.repository.JournalEntryRepository;
import com.hustlertechnology.JournalApplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void saveEntry( User user){
        userRepository.save(user);
    }
    public List<User> getEntryList(){
        return userRepository.findAll();
    }
    public Optional<User> getById(ObjectId givenId){
        return userRepository.findById(givenId);
    }

    public boolean deleteEntry(ObjectId Id){
        userRepository.deleteById(Id);
        return true;
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}
