package dev.patricksilva.crud.models.security.services;

import dev.patricksilva.crud.models.entities.Role;
import dev.patricksilva.crud.models.entities.User;
import dev.patricksilva.crud.models.repository.PeopleRepository;
import dev.patricksilva.crud.models.shared.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = peopleRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        // Converte List<Role> para Set<Role>
        Set<Role> roles = new HashSet<>(user.getRoles());

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), roles);

        return UserDetailsImpl.build(userDTO);
    }
}
