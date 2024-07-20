package com.hustlertechnology.JournalApplication.service;

import com.hustlertechnology.JournalApplication.entity.JournalEntry;
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

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getEntryList(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId givenId){
        return journalEntryRepository.findById(givenId);
    }

    public boolean deleteEntry(ObjectId Id){
        journalEntryRepository.deleteById(Id);
        return true;
    }


}
