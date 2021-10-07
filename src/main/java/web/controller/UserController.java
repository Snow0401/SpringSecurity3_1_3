package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.models.User;
import web.services.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello! Spring MVC-SECURITY");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @GetMapping(value = "/user")
    public String printUserPage(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "user";
    }

    @GetMapping(value = "/admin")
    public String printAdminPage(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "admin";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

}



