package com.example.demo.Repository;

import com.example.demo.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface JournalEntryRepository extends MongoRepository <JournalEntry, ObjectId>{
}
