package ru.ea_dm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ea_dm.models.Contact;
import ru.ea_dm.repositories.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactService {
    private final ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Transactional
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public Optional<Contact> findById(int id) {
        return contactRepository.findById(id);
    }

    public Optional<Contact> findByLink(String link) {
        return contactRepository.findByLink(link);
    }

    @Transactional
    public void update(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void delete(int id) {
        contactRepository.deleteById(id);
    }
}
