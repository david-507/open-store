package org.dmace.store.controller.admin;

import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

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

    /** Deletes multiple products! */
    @DeleteMapping("/products")
    public ResponseEntity delete(@RequestBody List<Long> ids){
        productservice.removeAll(ids);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
