package ru.ea_dm.data;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.ea_dm.models.Contact;
import ru.ea_dm.models.User;
import ru.ea_dm.models.enums.Role;
import ru.ea_dm.services.ContactService;
import ru.ea_dm.services.UserService;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final ContactService contactService;

    // TODO: Создание временного администратора. Не забыть убрать!
    private void saveAdmin() {
        if (userService.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("change@mail.com");
            admin.getRoles().add(Role.ROLE_ADMIN);
            admin.setActive(true);
            admin.setPassword("admin");
            userService.register(admin);
        }
    }

    private void setDefaultContacts() {
        if (contactService.findByName("default").isEmpty()) {
            Contact contact = new Contact();
            contact.setName("default");
            contact.setVk("");
            contact.setTelegram("");
            contact.setInstagram("");
            contact.setPhone("");
            contact.setAddress("");
            contactService.save(contact);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        saveAdmin();
        setDefaultContacts();
    }
}
