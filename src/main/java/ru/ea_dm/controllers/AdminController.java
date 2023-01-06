package ru.ea_dm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_page")
public class AdminController {

    @GetMapping()
    public String index() {
        return "admin/index";
    }
}
