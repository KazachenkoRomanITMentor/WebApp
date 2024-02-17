package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.services.UserServices;

@Controller
public class UserController {

    private final UserServices userServices;
    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping({"","/","/list"})
    public String getUsers(Model model) {
        model.addAttribute("users", userServices.getAllUser());
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userServices.readUser(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        userServices.createOrUpdateUser(user);
        return "redirect:/list";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, Model model){
        user.setId(id);
        userServices.createOrUpdateUser(user);
        return "redirect:/list";

    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){
        User user = userServices.readUser(id);
        model.addAttribute("user", user);
        return "confirmDelete";
    }

    @GetMapping("/confirmDelete/{id}")
    public  String confirmDelete(@PathVariable("id") Long id){
        userServices.deleteUser(id);
        return "redirect:/list";
    }

    @GetMapping("/create")
    public String createUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "create";
    }
}





