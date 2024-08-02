package com.hustlertechnology.JournalApplication.repository;

import com.hustlertechnology.JournalApplication.entity.JournalEntry;
import com.hustlertechnology.JournalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRepository extends MongoRepository<User, ObjectId> {

        User findByUserName(String username);
        User deleteByUserName(String username);

}
