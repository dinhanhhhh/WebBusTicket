/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.service.CoachService;
import com.dtk.service.RouteService;
import com.dtk.service.TripService;
import com.dtk.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author XGEAR
 */
@Controller
@ControllerAdvice
public class IndexController {

    @Autowired
    private TripService tripService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("routes", this.routeService.getRoutes(null));
        model.addAttribute("coaches", this.coachService.getCoachs(null));
        model.addAttribute("userDriver", this.userService.getUserByRole("ROLE_DRIVER"));
    }

    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(required = false) Map<String, String> params) {
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
//        model.addAttribute("trips", this.tripService.getTrips(params, 0));         
        model.addAttribute("trips", this.tripService.getTrips(kw, page));
        model.addAttribute("tripCounter", this.tripService.countTrip());
        return "index";
    }
}

//    @GetMapping("/trips/{tripId}")
//    public String tripDetail(Model model, @PathVariable(value = "tripId") int id) {
//        model.addAttribute("tripId", this.tripService.getTripById(id));
//        return "details";
//    }
//}
