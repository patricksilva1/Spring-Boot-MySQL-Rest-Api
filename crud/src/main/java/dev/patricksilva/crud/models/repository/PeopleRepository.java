package dev.patricksilva.crud.models.repository;

import dev.patricksilva.crud.models.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
}