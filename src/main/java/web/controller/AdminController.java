package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.services.RoleService;
import web.services.UserService;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
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

    @GetMapping("/getAuthorizedUser")
    public ResponseEntity<User> getAuthorizedUser(Principal principal) {
        return ResponseEntity.ok().body(userService.getUserByEmail(principal.getName()));
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<User> show(@PathVariable long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.createNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.editUserById(user);
        return ResponseEntity.ok().body(user);
    }










/*
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


    @PostMapping(value = "/create")
    public ResponseEntity<User> create(@RequestParam(value = "box", defaultValue = "2") int[] roles,
                             @RequestBody User user) {
        user.setRoles(prepareUser(roles));
        userService.createNewUser(user);
        return ResponseEntity.ok().body(user);
    }


    @PutMapping("/{id}/update")
    public String update(@RequestParam(value = "box", defaultValue = "2") int[] roles, @ModelAttribute User user) {
        user.setRoles(prepareUser(roles));
        userService.editUserById(user);
        return "redirect:/admin";
    }*/


}
