package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
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

    @GetMapping(value = "/adminview")
    public String printUserPage(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "adminview";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("admin", userService.getUserByEmail(principal.getName()));
        model.addAttribute("roleList", roleService.getAllRoles());
        return "new";
    }


    @PostMapping()
    public String createUser(@RequestParam(value = "box", defaultValue = "2") int[] roles,
                             @ModelAttribute User user) {
        user.setRoles(prepareUser(roles));
        userService.createNewUser(user);
        return "redirect:/admin";
    }







    @PutMapping("/{id}/update")
    public String update(@RequestParam(value = "box", defaultValue = "2") int[] roles, @ModelAttribute User user) {
        user.setRoles(prepareUser(roles));
        userService.editUserById(user);
        return "redirect:/admin";
    }


}
