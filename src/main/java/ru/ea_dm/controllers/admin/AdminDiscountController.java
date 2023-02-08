package ru.ea_dm.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ea_dm.models.Discount;
import ru.ea_dm.services.DiscountService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/discounts")
public class AdminDiscountController {
    private final DiscountService discountService;

    @GetMapping
    public String discounts(Model model) {
        model.addAttribute("discounts", discountService.findAll());
        return "admin/discounts/discounts";
    }

    @GetMapping("/new")
    public String newDiscountPage(@ModelAttribute("discount") Discount discount) {
        return "admin/discounts/new";
    }

    @PostMapping
    public String newDiscount(@ModelAttribute("discount") @Valid Discount discount,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/discounts/new";
        } else {
            discountService.save(discount);
            return "redirect:/admin/discounts";
        }
    }

    @GetMapping("/{id}/edit")
    public String editDiscountPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("discount", discountService.findById(id).get());
        return "admin/discounts/edit";
    }


    @PatchMapping("/{id}")
    public String editDiscount(@PathVariable("id") Long id,
                               @ModelAttribute("discount") @Valid Discount discount,
                               BindingResult bindingResult) {
        discount.setDiscountId(id);
        if (bindingResult.hasErrors()) {
            return "admin/discounts/edit";
        } else {
            discountService.update(discount);
            return "redirect:/admin/discounts";
        }

    }

    @DeleteMapping("/{id}")
    public String deleteDiscount(@PathVariable("id") Long id) {
        discountService.delete(id);
        return "redirect:/admin/discounts";
    }
}
