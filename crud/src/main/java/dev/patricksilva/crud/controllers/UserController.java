package dev.patricksilva.crud.controllers;

import dev.patricksilva.crud.models.entities.User;
import dev.patricksilva.crud.models.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping
    public ResponseEntity<List<User>> getAllPeople() {
        List<User> userList = peopleService.getAllPeople();
        return userList.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getPeopleById(@PathVariable Long id) {
        User person = peopleService.getPeopleById(id);
        return person == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(person);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getPeopleByEmail(@PathVariable String email) {
        User person = peopleService.getPeopleByEmail(email);
        return person == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Void> savePeople(@RequestBody User user) {
        peopleService.savePeople(user);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeople(@PathVariable Long id) {
        User person = peopleService.getPeopleById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        peopleService.deletePeople(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestParam String password) {
        peopleService.updatePassword(id, password);
        return ResponseEntity.noContent().build();
    }
}