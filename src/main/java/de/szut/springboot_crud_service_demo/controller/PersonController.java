package de.szut.springboot_crud_service_demo.controller;

import java.util.List;

import de.szut.springboot_crud_service_demo.dao.PersonDao;
import de.szut.springboot_crud_service_demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("springboot-crud-service")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personDao.readAll());
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<Person>> getPersonByURI(@PathVariable("id") int id) {
        Person personRead = personDao.read(id);
        if (personRead == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.ok(List.of(personRead));
    }

    @PostMapping("/person")
    public ResponseEntity<List<Person>> addPerson(@RequestBody Person person) {
        Person newPerson = personDao.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(List.of(newPerson));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<List<Person>> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
        Person toUpdate = personDao.read(id);
        Person updated = personDao.update(id, person);
        if (toUpdate == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.ok(List.of(updated));
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePersonByURI(@PathVariable("id") int id) {
        Person toDelete = personDao.read(id);
        if (toDelete == null) {
            return ResponseEntity.notFound().build();
        } else {
            personDao.delete(id);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/person")
    public ResponseEntity<Void> deletePersonByParam(@RequestParam int id) {
        Person personToDelete = personDao.read(id);
        if (personToDelete == null) {
            return ResponseEntity.notFound().build();
        } else {
            personDao.delete(id);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to the Person Service!");
    }
}
