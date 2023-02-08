package ru.ea_dm.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ea_dm.models.Contact;
import ru.ea_dm.services.ContactService;
import ru.ea_dm.util.ContactValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contacts")
public class ContactController {
    private final ContactService contactService;
    private final ContactValidator contactValidator;

    @GetMapping
    public String show(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "admin/contacts/show";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("contact") Contact contact) {
        return "admin/contacts/new";
    }

    @PostMapping
    public String create(@ModelAttribute("contact") @Valid Contact contact,
                         BindingResult bindingResult) {
        contactValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/contacts/new";
        } else {
            contactService.save(contact);
            return "redirect:/admin/contacts";
        }
    }
    // TODO: редактирование не работает
    @GetMapping("/{id}/edit")
    public String updatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute(contactService.findById(id).get());
        return "admin/contacts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
            @ModelAttribute("contact") @Valid Contact contact,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/contacts/edit";
        } else {
            contact.setContactId(id);
            contactService.update(contact);
            return "redirect:/admin/contacts";
        }
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") int id) {
        contactService.delete(id);
        return "redirect:/admin/contacts";
    }

}
