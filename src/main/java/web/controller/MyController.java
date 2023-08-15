package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
public class MyController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String showUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users-list";
    }

    @RequestMapping("/add_new_user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-create";
    }

    @RequestMapping("/save_user")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() != 0) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return "redirect:/";
    }

    @RequestMapping("/update_user")
    public String updateUser(@RequestParam("userId") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-create";
    }

    @RequestMapping("/delete_user")
    public String deleteUser(@RequestParam("userId") long id) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
        return "redirect:/";
    }
}
