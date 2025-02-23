package dev.patricksilva.crud.models.services;

import dev.patricksilva.crud.models.entities.Role;
import dev.patricksilva.crud.models.entities.User;
import dev.patricksilva.crud.models.exception.ResourceNotFoundException;
import dev.patricksilva.crud.models.repository.PeopleRepository;
import dev.patricksilva.crud.models.repository.RoleRepository;
import dev.patricksilva.crud.models.utils.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void savePeople(User pessoa) {
        pessoa.setPassword(passwordService.encodePassword(pessoa.getPassword()));
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });
        pessoa.getRoles().add(defaultRole);
        peopleRepository.save(pessoa);
    }

    @Override
    public List<User> getAllPeople() {
        return peopleRepository.findAll();
    }

    @Override
    public User getPeopleById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public User getPeopleByEmail(String email) {
        return peopleRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void deletePeople(Long id) {
        User user = peopleRepository.findById(id).orElse(null);
        if (user != null) {
            user.getProducts().clear();
            peopleRepository.delete(user);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return peopleRepository.existsByEmail(email);
    }

    @Override
    public void updatePassword(Long id, String password) {
        User user = peopleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found!"));
        user.setPassword(passwordService.encodePassword(password));
        peopleRepository.save(user);
    }
}