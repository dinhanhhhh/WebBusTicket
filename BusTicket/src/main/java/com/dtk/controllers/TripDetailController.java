/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Coach;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import com.dtk.service.CoachService;
import com.dtk.service.SeatService;
import com.dtk.service.TicketService;
import com.dtk.service.TripService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author XGEAR
 */

@Controller
@RequestMapping("/trips")
public class TripDetailController {

    @Autowired
    private TripService tripService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/{id}")
    public String index(Model model,
            @PathVariable(value = "id") String id,
            @RequestParam Map<String, String> params, HttpSession session) {
        User u = (User) session.getAttribute("currentUser");
        model.addAttribute("current", u);
        model.addAttribute("id", tripService.getTripByID(id));
        return "trip-detail";
    }

    @GetMapping("/book_ticket/{idTrip}")
    public String bookTicket(Model model,
            @PathVariable(value = "idTrip") int idT,
            @RequestParam Map<String, String> params, HttpSession session) {
        
        model.addAttribute("tripT", this.tripService.getTripById(idT));
        
        Trip trip = this.tripService.getTripById(idT);
        Coach coach = trip.getIdCoach();
        int idCoach = coach.getId();
        model.addAttribute("idCoach", this.coachService.getCoachByID(idCoach));
        model.addAttribute("ticketDetail", this.ticketService.getTicketDetailByIDTrip(idT));
        
        return "book-ticket";
    }
}
