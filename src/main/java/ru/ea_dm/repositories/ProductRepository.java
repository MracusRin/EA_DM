package ru.ea_dm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ea_dm.models.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByTitle(String title);
}
