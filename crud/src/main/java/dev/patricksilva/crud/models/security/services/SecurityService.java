package dev.patricksilva.crud.models.security.services;

import dev.patricksilva.crud.models.repository.PeopleRepository;
import dev.patricksilva.crud.models.shared.PeopleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service class for security-related operations.
 *
 * @author Patrick L. da Silva
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private PeopleRepository peopleRepository;

    /**
     * Loads a user by the given email.
     *
     * @param email The email of the user to be loaded.
     * @return The UserDetails of the loaded user.
     * @throws UsernameNotFoundException If the user with the specified email is not
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PeopleDTO peopleDTO = peopleRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return UserDetailsImpl.build(peopleDTO);
    }
}