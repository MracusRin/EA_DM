package ru.ea_dm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ea_dm.models.Person;
import ru.ea_dm.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PersonService personService;

    @Autowired
    public PersonDetailService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personService.findByUsername(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            return new PersonDetails(person.get());
        }
    }
}
