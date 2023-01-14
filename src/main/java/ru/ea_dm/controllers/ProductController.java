package ru.ea_dm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ea_dm.models.Product;
import ru.ea_dm.services.ImageService;
import ru.ea_dm.services.ProductService;
import ru.ea_dm.util.ProductValidator;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductValidator productValidator;
    private final ImageService imageService;

    @GetMapping
    public String product(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/product";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") @Valid Product product,
                         @RequestParam("image") MultipartFile image,
                         BindingResult bindingResult) throws IOException {
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "products/new";
        } else {
            productService.save(product);
            imageService.save(image, product);
            return "redirect:/products";
        }
    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id).get());
        return "products/show";
    }

    @GetMapping("/{id}/edit")
    public String updatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id).get());
        return "products/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

}
