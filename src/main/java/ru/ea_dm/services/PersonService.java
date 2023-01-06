package ru.ea_dm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Person;
import ru.ea_dm.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findByUsername(String name) {
        return personRepository.findByUsername(name);
    }

    @Transactional
    public void registration(Person person) {
        person.setCreated_at(LocalDateTime.now());
        personRepository.save(person);
    }
}
