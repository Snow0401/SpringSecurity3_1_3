package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    private void prepareUser(User user) {
        for (Role role : user.getRoles()) {
            role.setId(roleService.getRoleByName(role.getRoleName()).getId());
        }
    }


    @GetMapping("users")
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("users/{id}")
    public String printUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/info";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user) {
        prepareUser(user);
        userService.createNewUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());
        return "users/new";
    }

    @GetMapping("users/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleList", roleService.getAllRoles());
        return "/users/edit";
    }

    @PatchMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        prepareUser(user);
        userService.editUserById(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

}
