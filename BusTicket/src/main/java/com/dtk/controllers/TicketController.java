/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author GIGABYTE
 */
@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/tickets")
    public String getTickets(Model model) {
        model.addAttribute("tickets", this.ticketService.getTicketBook(null));
        return "tickets";
    }
}
