package com.dating.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.dating.entity.Gender;
import com.dating.entity.User;
import com.dating.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    // ✅ Constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/recommendation")
    public String getRecommendations(@RequestParam("userId") Long id,
                                     @RequestParam(defaultValue = "2") int topN,
                                     Model model) {
        try {
            User user = userService.findUserById(id);
            List<User> recommendations = userService.recommendMatches(id, topN);

            model.addAttribute("user", user);
            model.addAttribute("recommendations", recommendations);
            model.addAttribute("topN", topN);

            return "recommendation"; 
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "recommendation";
        }
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam int age,
                               @RequestParam String gender,
                               @RequestParam String interests) {
        List<String> interestsList = Arrays.asList(interests.split("\\s*,\\s*"));
        User user = new User(name, Gender.valueOf(gender.toUpperCase()), age, interestsList);

        userService.saveUser(user);
        return "redirect:/users/"; 
    }
}


