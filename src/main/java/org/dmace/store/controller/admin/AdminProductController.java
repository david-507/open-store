package org.dmace.store.controller.admin;

import org.dmace.store.model.Producto;
import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productservice;

    @GetMapping("/create")
    public String showCreate(@ModelAttribute("product") Producto product, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/product/product-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model) {
        Optional<Producto> p = productservice.findById(id);

        if(p.isPresent()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("product", p.get());
            return "/admin/product/product-form";
        }

        return "redirect:/admin/products";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("product") Producto product, Model model) {
        productservice.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productservice.findAll());
        return "admin/products";
    }
}
