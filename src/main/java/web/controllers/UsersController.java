package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.showAllUsers());
        return "users/all-users";
    }

    @GetMapping("/user/{id}")
    public String showUserbyId(@PathVariable("id") long id, Model model) {
        model.addAttribute("showUser", userService.showUserById(id));
        return "users/user";
    }

    @GetMapping("/new-user")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new-user";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new-user";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit-user/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.showUserById(id));
        return "users/edit-user";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit-user";
        }
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
