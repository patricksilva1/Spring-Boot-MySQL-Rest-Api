package dev.patricksilva.crud.models.services;

import dev.patricksilva.crud.models.entities.User;

import java.util.List;

public interface PeopleService {
    List<User> getAllPeople();

    User getPeopleById(Long id);

    User getPeopleByEmail(String email);

    void savePeople(User user);

    void deletePeople(Long id);

    boolean existsByEmail(String email);

    void updatePassword(Long id, String password);
}