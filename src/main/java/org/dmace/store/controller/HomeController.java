package org.dmace.store.controller;

import org.dmace.store.model.Producto;
import org.dmace.store.service.CategoryService;
import org.dmace.store.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String welcome(@RequestParam(name="cid", required=false) Long cid, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        List<Producto> productos;

        if(cid==null)
            model.addAttribute("products", productservice.getRandomProducts(RANDOM_PRODUCTS_TO_SHOW));
        else
            model.addAttribute("products", productservice.findAllByCategory(cid));

        model.addAttribute("message", "welcome to spring booy");
        return "index";
    }

    @GetMapping("/test")
    public String hello(Model model) {
        return "test";
    }
}
