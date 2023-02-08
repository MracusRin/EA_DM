package ru.ea_dm.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ea_dm.models.Contact;
import ru.ea_dm.services.ContactService;

@Component
@RequiredArgsConstructor
public class ContactValidator implements Validator {
    private final ContactService contactService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contact contact = (Contact) target;
        if (contactService.findByLink(contact.getLink()).isPresent()) {
            errors.rejectValue("link", "", "Контакт с таким значением уже существует");
        }
    }
}
