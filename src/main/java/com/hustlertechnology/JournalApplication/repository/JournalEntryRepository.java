package com.hustlertechnology.JournalApplication.repository;

import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {

}
