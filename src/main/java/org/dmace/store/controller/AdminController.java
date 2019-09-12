package org.dmace.store.controller;

import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productservice;

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());

        return "admin/categories";
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productservice.findAll());
        return "admin/products";
    }
}
