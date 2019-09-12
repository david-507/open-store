package org.dmace.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/")
    public String welcome(Model model) {
        logger.info("welcome controller");

        model.addAttribute("message", "welcome to spring booy");
        return "index";
    }
}
