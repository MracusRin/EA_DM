package ru.em_da.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.em_da.models.Product;
import ru.em_da.services.PriceService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public String price(Model model) {
        model.addAttribute("products", priceService.index());
        return "/price/price";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "/price/new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") Product product) {
        priceService.save(product);
        return "redirect:/price";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", priceService.show(id));
        return "/price/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        priceService.delete(id);
        return "redirect:/price";
    }
}
