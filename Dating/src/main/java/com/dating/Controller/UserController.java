package com.dating.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.dating.Entity.Gender;
import com.dating.Entity.User;
import com.dating.Service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("recommendations", users);
        return "index";
    }


    @GetMapping("/recommendations")
    public String showRecommendationsPage() {
        return "recommendation";
    }

   
    @GetMapping("/recommendation")
    public String getRecommendations(@RequestParam("userId") Long id, @RequestParam(defaultValue = "2") int topN, Model model) {
        try {
            User user = userService.findUserById(id);
            System.out.println("Id :"+id+topN);
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



    @PostMapping("/users")
    public String registerUser(@RequestParam String name, @RequestParam int age, 
                                @RequestParam String gender, @RequestParam String interests) {
        List<String> interestsList = Arrays.asList(interests.split("\\s*,\\s*"));

        User user = new User(name, Gender.valueOf(gender), age, interestsList);

        userService.saveUser(user);

        return "redirect:/"; 
    }
}
