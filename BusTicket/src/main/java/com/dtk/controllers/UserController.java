/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.User;
import com.dtk.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author GIGABYTE
 */
@Controller
public class UserController {

    @Autowired
    private UserService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(value = "user") @Valid User user,
            BindingResult rs) {

        if (!rs.hasErrors()) {
            String errMsg = "";
            if (user.getPassword().equals(user.getConfirmPassword())) {
                if (this.userDetailsService.addUser(user) == true) {
                    return "redirect:/login";
                } else {
                    errMsg = "Da co loi xay ra!";
                }
            } else {
                errMsg = "Mat khau khong khop!";
            }
            model.addAttribute("errMsg", errMsg);
        }
        return "register";
    }

    @GetMapping("/userdetails")
    public String userDetails(Model model) {
        return "user-details";
    }
}
