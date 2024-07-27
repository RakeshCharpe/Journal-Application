package com.hustlertechnology.JournalApplication.service;

import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import com.hustlertechnology.JournalApplication.entity.User;
import com.hustlertechnology.JournalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }
    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getEntryList(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId givenId){
        return journalEntryRepository.findById(givenId);
    }

    public void deleteEntry(ObjectId Id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(Id);
    }


}
