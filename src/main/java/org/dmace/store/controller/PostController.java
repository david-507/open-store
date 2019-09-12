package org.dmace.store.controller;

import org.dmace.store.model.bean.PostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//@Controller
public class PostController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/post/create")
    public String createNewPost(@ModelAttribute("pb") PostBean pb) {
        String result = "post";
        if( loggedOut() )
            result = "redirect:/login?a=post";

        return result;
    }

    @PostMapping("/post/create")
    public String createPost(@Valid @ModelAttribute("pb") PostBean pb, BindingResult br) {
        String result = "post";
        if( loggedOut() )
            result = "redirect:/login";

        return result;
    }

    /** returns true if there is no user in session */
    private boolean loggedOut() {
        boolean loggedOut = true;
        HttpSession session = request.getSession(false);
        if( session!=null && session.getAttribute("user")!=null )
            loggedOut = false;

        return loggedOut;
    }
}
