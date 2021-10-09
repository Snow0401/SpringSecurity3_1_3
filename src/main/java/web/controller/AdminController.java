package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin/")
public class AdminController {


    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private Set<Role> prepareUser (int[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (int i=0; i < roles.length; i++) {
            roleSet.add(roleService.getRoleById(roles[i]));
        }
        return roleSet;
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
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam (name ="roles") int[] roles) {
        user.setRoles(prepareUser(roles));
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
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam (name ="roles") int[] roles) {
        user.setRoles(prepareUser(roles));
        userService.editUserById(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

}
