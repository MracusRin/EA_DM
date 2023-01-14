package ru.ea_dm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ea_dm.services.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String product(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/product";
    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id).get());
        return "products/show";
    }

}
