package ru.ea_dm.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ea_dm.models.ServiceIml;
import ru.ea_dm.services.ServiceImplService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/services")
public class AdminServiceImplController {
    private final ServiceImplService serviceImplService;

    @GetMapping
    public String adminServices(Model model) {
        model.addAttribute("services", serviceImplService.findAll());
        return "admin/services/services";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("service") ServiceIml serviceIml) {
        return "admin/services/new";
    }

    // TODO: Добавить валидацию создания сервиса
    @PostMapping
    public String create(@ModelAttribute("service") ServiceIml service) {
        serviceImplService.save(service);
        return "redirect:/admin/services";

    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("service", serviceImplService.findById(id));
        return "admin/services/show";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("service", serviceImplService.findById(id));
        return "admin/services/edit";
    }

    // TODO: Добавить валидацию редактирования сервиса
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("service") ServiceIml service,
                       @PathVariable("id") Long id) {
        service.setServiceId(id);
        serviceImplService.update(service);
        return "redirect:/admin/services";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        serviceImplService.delete(id);
        return "redirect:/admin/services";
    }
}
