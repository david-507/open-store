package org.dmace.store.controller;

import org.dmace.store.model.User;
import org.dmace.store.model.bean.RegisterBean;
import org.dmace.store.service.RegisterService;
import org.dmace.store.util.LabelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegisterController {
    private static final List<String> CITIES = Arrays.asList("Barcelona", "Madrid", "Valencia", "Segovia");
    private static final List<LabelValue> GENRES = Arrays.asList(new LabelValue("Hombre", "H"), new LabelValue("Mujer", "M"), new LabelValue("Otros", "O"));
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService service;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("cities", CITIES);
        model.addAttribute("genders", GENRES);
        model.addAttribute("fragment", "register");
    }

    @GetMapping("/signup")
    public String register(@ModelAttribute("rb") RegisterBean rb){
        return "main-login";
    }

    @PostMapping("/signup")
    public String doRegister(@Valid @ModelAttribute("rb") RegisterBean rb, BindingResult br, Model model) {
        String result = "main-login";
        if(br.hasErrors()){
            //
            model.addAttribute("rb", rb);
            logger.info("Binding result has errors");
        } else {
            User user = null;
            try {
                user = service.register(rb);
                session.setAttribute("user", user);

                result = "redirect:/";
            } catch (DataIntegrityViolationException e) {
                logger.warn("Oups.. trying to add a new user with an existing email ({}) ?", rb.getEmail());
                logger.warn(e.getMessage());
                br.addError(new FieldError("rb", "email", "Sorry, email \"" + rb.getEmail() + "\" is already taken"));
            }
        }
        return result;
    }



}
