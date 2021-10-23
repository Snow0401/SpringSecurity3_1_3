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
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("user")
    public String ShowUser() {
        return "user";
    }

    @GetMapping("admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

}



