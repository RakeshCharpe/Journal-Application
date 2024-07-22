package com.hustlertechnology.JournalApplication.controller;


import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import com.hustlertechnology.JournalApplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class UserController {

    @Autowired
    JournalEntryService journalEntryService;


    @GetMapping
    public ResponseEntity<?> getAll() { //localhost:8080/journal
        List<JournalEntry> all = journalEntryService.getEntryList();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Path Variable  : /Journal/id/rakesh
    //Request Parameter :  /Journal/id?name=rakesh
    //@RequestBody : Jab body se value leni hoti hai

    @GetMapping("id/{gettingId}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId gettingId) {
        //Optional means ek box hai usme data ho sakta hai nahi bhi ho sakta hai

        Optional<JournalEntry> journalEntry = journalEntryService.getById(gettingId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry EntryData) {
        //localhost:8080/journal Journal Entry ka hi object pass kiya tha isliye yaha usi ko liya and add kar rahe hai
        try {
            EntryData.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(EntryData);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("id/{gettingId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId gettingId) {

        Optional<JournalEntry> journalEntry = journalEntryService.getById(gettingId);

        if (journalEntry.isPresent()) {
            journalEntryService.deleteEntry(gettingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry dataToUpdate) {

        JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(dataToUpdate.getTitle());
            oldEntry.setContent(dataToUpdate.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
