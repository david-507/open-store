package org.dmace.store.controller;

import org.dmace.store.model.Producto;
import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/product/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        Optional<Producto> product = productService.findById(id);

        if(product.isPresent()) {
            model.addAttribute("product", product.get());
            return "detalle-producto";
        }

        return "redirect:/";

    }

}
