package org.dmace.store.controller.admin;

import org.dmace.store.model.Categoria;
import org.dmace.store.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private final Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/new")
    public String categories(@ModelAttribute("category") Categoria category, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category", new Categoria());

        return "/admin/category/category-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<Categoria> c = categoryService.findById(id);
        if(c.isPresent()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("category", c.get());
            return "/admin/category/category-form";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String products(@PathVariable("id") Long cid, Model model){
        String result = "redirect:/admin/categories";
        model.addAttribute("categories", categoryService.findAll());

        Optional<Categoria> categoria = categoryService.findById(cid);

        if(categoria.isPresent()) {
            int count = categoryService.countProductsByCategory(categoria.get());
            if( count == 0 )
                categoryService.delete(categoria.get());
            else
                result = result + "?error=" + count;
        }

        return result;
    }

    @PostMapping("/new")
    public String products(@ModelAttribute Categoria category, Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("message", "Categoria '" + category.getNombre() + "' creada");
        categoryService.create(category);

        return "redirect:/admin/categories";
    }

    /* ajax request: number of products from given category */
    @GetMapping("/{id}/products-count")
    public ResponseEntity<Integer> countProductsByCategory(@PathVariable("id") Long catid) {
        int num = categoryService.countProductsByCategory(catid);
        return ResponseEntity.of(Optional.of(num));
    }

}
