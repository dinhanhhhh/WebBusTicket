/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Seat;
import com.dtk.pojo.Ticket;
import com.dtk.pojo.TicketDetail;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import com.dtk.service.SeatService;
import com.dtk.service.TicketService;
import com.dtk.service.TripService;
import com.dtk.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author GIGABYTE
 */
@RestController
@RequestMapping("/api")
public class ApiTicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;
    @Autowired
    private SeatService seatService;

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDetail>> getTickets() {
        return new ResponseEntity<>(this.ticketService.getTickets(null), HttpStatus.OK);
    }

    @PostMapping(path = "/tickets", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<TicketDetail> addSeatTicketDetail(@RequestBody Map<String, String> params) {
        try {
            int idTicket = Integer.parseInt(params.get("idTicket"));
            Ticket ticket = this.ticketService.getTicketBookByID(idTicket);

            int idSeat = Integer.parseInt(params.get("idSeat"));
            Seat seat = this.seatService.getSeatByID(idSeat);

            TicketDetail ticketDetail = this.ticketService.addSeatTicketDetail(ticket, seat);
            return new ResponseEntity<>(ticketDetail, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/bookticket")
    public ResponseEntity<List<Ticket>> getTicketBook() {
        return new ResponseEntity<>(this.ticketService.getTicketBook(null), HttpStatus.OK);
    }

    @PostMapping(path = "/bookticket", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Ticket> addTicket(@RequestBody Map<String, String> params) {
        try {
//            User user = this.userService.getUserById(Integer.parseInt(params.get("idCustomerLogin")));
//            int idUser = user.getId();
            Trip trip = this.tripService.getTripById(Integer.parseInt(params.get("idTrip")));
            int idTrip = trip.getId();

            Ticket t = this.ticketService.addTicket(params, idTrip);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/ticketdetail/{idTicket}")
    public ResponseEntity<List<TicketDetail>> getTicketDetail(@PathVariable(value = "idTicket") int idTicket) {
        return new ResponseEntity<>(this.ticketService.getTicketDetailByIDTicket(idTicket), HttpStatus.OK);
    }

    @GetMapping("/ticketdetail/myTicketBook/{idUser}")
    public ResponseEntity<List<Ticket>> getTicketBookByIDUser(@PathVariable(value = "idUser") int idUser) {
        return new ResponseEntity<>(this.ticketService.getTicketBookByIDUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/ticketdetail/myTicket/{idUserLogin}")
    public ResponseEntity<List<TicketDetail>> getTicketDetailByIDUserLogin(@PathVariable(value = "idUserLogin") int idUserLogin) {
        return new ResponseEntity<>(this.ticketService.getTicketDetailByIDUserLogin(idUserLogin), HttpStatus.OK);
    }

    @GetMapping("/ticketdetail/getTicketDetail/{idTicket}")
    public ResponseEntity<List<Ticket>> getTicketByID(@PathVariable(value = "idTicket") int idTicket) {
        List<Ticket> ticket = new ArrayList<>();
        ticket.add(this.ticketService.getTicketBookByID(idTicket));
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PutMapping("/xacNhanTicket/{idTicket}")
    public boolean xacNhanTicket(Model model,
            @PathVariable(value = "idTicket") int idTicket) {
        if (this.ticketService.xacNhanTicket(idTicket) == true) {
            return true;
        }
        return false;
    }

    @DeleteMapping("/tickets/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable(value = "ticketId") int ticketId) {
        try {
            Ticket ticket = this.ticketService.getTicketBookByID(ticketId);
            if (ticket != null) {
                this.ticketService.deleteTicket(ticketId); // Gọi phương thức xóa trong TicketService
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
