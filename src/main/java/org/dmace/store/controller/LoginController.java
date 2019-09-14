package org.dmace.store.controller;

import org.dmace.store.model.User;
import org.dmace.store.model.bean.LoginBean;
import org.dmace.store.repository.UserRepository;
import org.dmace.store.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static final String ACTION_PARAM_NAME = "a";
    private static final Map<String, String> VALID_ACTIONS_REDIRECTS = new HashMap<>();

    static {
        VALID_ACTIONS_REDIRECTS.put("post", "redirect:/post/create");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new LoginBean());
        return "main-login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid @ModelAttribute("userLogin") LoginBean lb, BindingResult br, Model model) {
//        String result = getAction();
        String result = "redirect:/";

        if(br.hasErrors()){
            model.addAttribute("error", "Nombre o contraseña incorrecto");
            return "main-login";
        }

        User user = userService.findByEmail(lb.getUsername());

        if( loginSuccess(lb, user) ) {
            session.setAttribute("user", user);
        } else {
//            model.addAttribute("userLogin", lb);
            model.addAttribute("error", "Nombre o contraseña incorrecto");
            return "main-login";
        }

        return result;
    }

    @GetMapping("/logout")
    public String doLogout() {
        if(session!=null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        return "redirect:/";
    }

    /** returns true if user hashed password matches with entered hashed password  */
    private boolean loginSuccess(LoginBean lb, User user) {
        return user != null && BCrypt.checkpw(lb.getPassword(), user.getPassword());
    }

    private String getAction() {
        String result = "redirect:/";
        String a = request.getParameter(ACTION_PARAM_NAME);

        if( a!=null ){
            String action = VALID_ACTIONS_REDIRECTS.get(a);
            if( action!=null )
                result = action;
        }

        return result;
    }
}