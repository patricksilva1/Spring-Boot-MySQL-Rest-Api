package dev.patricksilva.crud.models.services;

import dev.patricksilva.crud.models.entities.People;
import dev.patricksilva.crud.models.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    @Override
    public People getPeopleById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public void savePeople(People pessoa) {
        peopleRepository.save(pessoa);
    }

    @Override
    public void deletePeople(Long id) {
        People person = peopleRepository.findById(id).orElse(null);
        if (person != null) {
            person.getProducts().clear();
            peopleRepository.delete(person);
        }
    }
}