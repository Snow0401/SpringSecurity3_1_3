package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.services.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public String printUserPage(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "user";
    }

    @GetMapping(value = "/admin")
    public String printAdminPage(Principal principal, ModelMap model) {
        model.addAttribute("admin", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

}



