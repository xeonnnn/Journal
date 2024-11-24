package com.example.demo.Controller;


import com.example.demo.Service.JournalEntryService;
import com.example.demo.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

@Autowired
private JournalEntryService journalEntryService;


    @GetMapping
public List<JournalEntry>getAll(){
    return journalEntryService.getAll();
}

@PostMapping
public ResponseEntity createEntry(@RequestBody JournalEntry entry){
        try { entry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<> (entry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }

}


    @GetMapping("id/{myId}")
            public ResponseEntity getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.FindById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<> (journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


@DeleteMapping("id/{myId}")
public Boolean deleteEntryById(@PathVariable ObjectId myId){

journalEntryService.DeleteById(myId);
return true;

}

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalByID(@PathVariable ObjectId myId , @RequestBody JournalEntry newEntry){
        JournalEntry oldId = journalEntryService.FindById(myId).orElse(null);
        if (oldId != null) {
            oldId.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldId.getTitle() );
            oldId.setTitle(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldId.getContent() );

        }
        journalEntryService.saveEntry(oldId);
        return oldId;
}
}
