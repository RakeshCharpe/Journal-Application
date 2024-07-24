package com.hustlertechnology.JournalApplication.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// This is POJO Class plain object java class
@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true) // to make username unique
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef // parent child relationship created this journal stored the reference of it and attached to users
    private List<JournalEntry> journalEntries = new ArrayList<>();


}