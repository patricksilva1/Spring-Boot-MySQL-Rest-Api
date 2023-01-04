package dev.patricksilva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patricksilva.crud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
