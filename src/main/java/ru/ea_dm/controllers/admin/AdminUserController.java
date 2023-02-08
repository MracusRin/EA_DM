package ru.ea_dm.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ea_dm.models.User;
import ru.ea_dm.models.enums.Role;
import ru.ea_dm.services.UserService;
import ru.ea_dm.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users/show";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user,
                                   Model model) {
        model.addAttribute("roles", Role.values());
        return "admin/users/registration";
    }

    @PostMapping()
    public String registration(Model model,
                               @ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "admin/users/registration";
        } else {
            userService.register(user);
            return "redirect:/admin/users";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", Role.values());
        return "admin/users/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult,
                       Model model,
                       @PathVariable("id") Long id) {
        user.setUserId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "admin/users/edit";
        } else {
            userService.update(user, id);
            return "redirect:/admin/users";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
