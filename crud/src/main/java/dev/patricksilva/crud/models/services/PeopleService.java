package dev.patricksilva.crud.models.services;

import dev.patricksilva.crud.models.entities.People;

import java.util.List;

public interface PeopleService {
    List<People> getAllPeople();

    People getPeopleById(Long id);

    void savePeople(People people);

    void deletePeople(Long id);
}