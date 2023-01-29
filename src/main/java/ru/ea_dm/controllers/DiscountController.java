package ru.ea_dm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ea_dm.services.DiscountService;

@Controller
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    public String discounts(Model model) {
        model.addAttribute("discounts", discountService.findAllActive());
        return "discounts/discounts";
    }
}
