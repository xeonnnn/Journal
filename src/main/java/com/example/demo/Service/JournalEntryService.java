package com.example.demo.Service;

import com.example.demo.Repository.JournalEntryRepository;
import com.example.demo.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();

    }
    public Optional<JournalEntry> FindById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }
    public void DeleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
