package ru.ea_dm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.ea_dm.models.Contact;
import ru.ea_dm.services.ContactService;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
class GlobalControllerAdvice {
    private final ContactService contactService;

    @ModelAttribute("contactList")
    public List<Contact> contactList() {
        return contactService.findAll();
    }
}
