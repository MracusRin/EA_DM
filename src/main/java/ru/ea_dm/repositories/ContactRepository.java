package ru.ea_dm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ea_dm.models.Contact;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByName(String name);
}
