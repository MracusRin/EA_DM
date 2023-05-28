package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Contact;
import ru.ea_dm.repositories.ContactRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactService {
    private final ContactRepository contactRepository;

    @Transactional
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public Optional<Contact> findById(int id) {
        return contactRepository.findById(id);
    }

    @Transactional
    public void update(Contact contact) {
        contact.setName("default");
        contactRepository.save(contact);
    }

    public Optional<Contact> findByName(String name) {
        return contactRepository.findByName(name);
    }
}
