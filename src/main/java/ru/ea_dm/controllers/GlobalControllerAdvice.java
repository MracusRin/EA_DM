package ru.ea_dm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.ea_dm.models.Contact;
import ru.ea_dm.services.ContactService;

@ControllerAdvice
@RequiredArgsConstructor
class GlobalControllerAdvice {
    private final ContactService contactService;

    @ModelAttribute("contactList")
    public Contact getContactList() {
        return contactService.findByName("default").get();
    }
}
