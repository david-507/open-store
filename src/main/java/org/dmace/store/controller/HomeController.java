package org.dmace.store.controller;

import org.dmace.store.model.Categoria;
import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private static final int RANDOM_PRODUCTS_TO_SHOW = 6;
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productservice;

    @GetMapping("/")
    public String welcome(Model model) {
        logger.info("welcome");
        List<Categoria> categorias = categoryService.findAll();
        model.addAttribute("categories", categorias);
        model.addAttribute("products", productservice.getRandomProducts(RANDOM_PRODUCTS_TO_SHOW));
        logger.info("found {} cats", categorias.size());
        model.addAttribute("message", "welcome to spring booy");
        return "index";
    }
}
