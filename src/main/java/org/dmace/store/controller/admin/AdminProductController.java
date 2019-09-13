package org.dmace.store.controller.admin;

import org.dmace.store.model.Producto;
import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productservice;

    @GetMapping("/new")
    public String categories(@ModelAttribute("product") Producto product, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/product/new-product";
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productservice.findAll());
        return "admin/products";
    }
}
