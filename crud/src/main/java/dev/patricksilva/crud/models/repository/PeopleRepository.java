package dev.patricksilva.crud.models.repository;

import dev.patricksilva.crud.models.entities.People;
import dev.patricksilva.crud.models.shared.PeopleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    Boolean existsByEmail(String email);
    Optional<PeopleDTO> findByEmail(String email);
}