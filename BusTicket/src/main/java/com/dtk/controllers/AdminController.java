/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Coach;
import com.dtk.pojo.Route;
import com.dtk.pojo.User;
import com.dtk.service.CoachService;
import com.dtk.service.RouteService;
import com.dtk.service.SeatService;
import com.dtk.service.TripService;
import com.dtk.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author GIGABYTE
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private TripService tripService;

    @GetMapping("/users")
    public String list(Model model) {
        return "users";
    }

    @GetMapping("/users/add_user")
    public String addUserView(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }
    
    @PostMapping("/users/add_user")
    public String addUser(Model model, @ModelAttribute(value = "user") User user) {
        String errMsg = "";
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (this.userService.addUser(user) == true) {
                return "redirect:/admin/users";
            } else {
                errMsg = "Da co loi xay ra!";
            }
        } else {
            errMsg = "Mat khau khong khop!";
        }
        model.addAttribute("errMsg", errMsg);
        return "add-user";
    }

    @GetMapping("/coaches")
    public String listCoaches(Model model) {
        model.addAttribute("coach", new Coach());
        return "coaches";
    }

    @PostMapping("/coaches")
    public String addCoach(@ModelAttribute(value = "coach") @Valid Coach coach, 
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "coaches";
        }
        
        if (this.coachService.addCoach(coach) == true) {
            int totalSeat = coach.getTotalseat();
            int idCoach = coach.getId();
            if (this.seatService.addSeat(idCoach, totalSeat))
                return "redirect:/admin/coaches";
        }
        return "coaches";
    }

    @GetMapping("/routes")
    public String listRoutes(Model model) {
        model.addAttribute("route", new Route());
        return "routes";
    }

    @PostMapping("/routes")
    public String addRoute(@ModelAttribute(value = "route") @Valid Route route, 
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "routes";
        }

        if (this.routeService.addRoute(route) == true) {
            return "redirect:/admin/routes";
        }
        return "routes";
    }
    
    @GetMapping("/route-stats")
    public String routeStats(Model model ){
        model.addAttribute("stats", this.tripService.routeStats());
        return "route-stats";
    }
    
 
}
