package org.dmace.store.controller;

import org.dmace.store.model.User;
import org.dmace.store.model.bean.RegisterBean;
import org.dmace.store.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService service;

    @Autowired
    private HttpSession session;

    @GetMapping("/signup")
    public String register(@ModelAttribute("rb") RegisterBean rb){
        return "register";
    }

    @PostMapping("/signup")
    public String doRegister(@Valid @ModelAttribute("rb") RegisterBean rb, BindingResult br) {
        String result = "register";
        if(br.hasErrors()){
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
