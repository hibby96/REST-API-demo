package com.revature.SpringController;



import javax.validation.Valid;

import com.revature.Database.DAOImpl;
import com.revature.Objects.Person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API")
public class controller {
    //We'll define via the mapping annotations what needs to be done for each HTTP request at these endpoints.
    DAOImpl dao = new DAOImpl();

    @GetMapping("/people/{id}")
    public ResponseEntity getPersonById(@PathVariable(value = "id") int personid) {

        Person person = dao.getPerson(personid);
        if (person == null) {
            return ResponseEntity.status(404).body("Couldn't find someone with that ID");
        }
        
      return ResponseEntity.status(200).body(person);
        
    }
    
    @PostMapping("/people")
    public ResponseEntity addPerson(@Valid @RequestBody Person newperson) {

        if (dao.addPerson(newperson)) {  //addPerson will return a boolean if successful
            return ResponseEntity.status(200).body("Added new person to the DB");
        } else {
            return ResponseEntity.status(500).body("Issue with adding person to the DB, need to debug.");
        }

    }

    @PutMapping("/people/{id}")
    public ResponseEntity updatePerson(@PathVariable(value = "id") int personid, @Valid @RequestBody Person newperson) {

        if (dao.getPerson(personid) == null) {  //check to see if that resource even exists first
            return ResponseEntity.status(404).body("No person exists with ID " + personid);
        }   

        if (dao.updatePerson(newperson, personid)) {
            return ResponseEntity.status(200).body("Updated person with id " + personid);
        } else {
            return ResponseEntity.status(500).body("Problem with updating person on the server side, need to debug.");
        }
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable(value = "id") int personid) {

        if (dao.getPerson(personid) == null) {  //check to see if that resource even exists first
            return ResponseEntity.status(404).body("No person exists with ID " + personid);
        }   

        if (dao.deletePerson(personid)) {
            return ResponseEntity.status(200).body("Deleted person with id " + personid);
        } else {
            return ResponseEntity.status(500).body("Problem with deleting person on the server side, need to debug.");
        }

    }
    
}
