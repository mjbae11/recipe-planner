package org.michaelbae.recipeplanner.api.controller;

import org.michaelbae.recipeplanner.model.User;
import org.michaelbae.recipeplanner.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") //normal landing page
public class UserController
{
    private final UserDetailsServiceImpl userDetailsService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String getLandingPage()
    {
        return "landing";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }

    // Spring will take care of all of this
//    @PostMapping("/login")
//    public String logInIntoAccount(@ModelAttribute User user, Model model)
//    {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
//        if (userDetails != null)
//        {
//            return "redirect:/recipes";
//        }
//        model.addAttribute("error", "Something went wrong");
//        return "login";
//    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model)
    {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping ("/signup")
    public String registerUser(@ModelAttribute User user)
    {
        userDetailsService.registerUser(user);
        return "redirect:/login";
    }

    // helper method
    // onSuccessful Log in or Signup
}
