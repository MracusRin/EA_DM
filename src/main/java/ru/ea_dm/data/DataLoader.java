package ru.ea_dm.data;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.ea_dm.models.User;
import ru.ea_dm.models.enums.Role;
import ru.ea_dm.services.UserService;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserService userService;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveAdmin();
    }
}
