package ru.ea_dm.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ea_dm.models.Contact;
import ru.ea_dm.services.ContactService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contacts")
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public String show(Model model) {
        model.addAttribute("contact", contactService.findByName("default").get());
        return "admin/contacts/contacts";
    }

    @GetMapping("/{id}/edit")
    public String updatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute(contactService.findById(id).get());
        return "admin/contacts/edit";
    }

    // TODO: Добавить валидацию контактов
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("contact") Contact contact) {
        contact.setContactId(id);
        contactService.update(contact);
        return "redirect:/admin/contacts";
    }
}
