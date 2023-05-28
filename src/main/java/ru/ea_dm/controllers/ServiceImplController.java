package ru.ea_dm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ea_dm.services.ServiceImplService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceImplController {
    private final ServiceImplService serviceImplService;

    @GetMapping
    public String services(Model model) {
        model.addAttribute("services", serviceImplService.findAll());
        return "services/services";
    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("service", serviceImplService.findById(id));
        return "services/show";
    }
}
