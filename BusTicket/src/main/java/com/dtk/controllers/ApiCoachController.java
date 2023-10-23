/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Coach;
import com.dtk.pojo.Seat;
import com.dtk.service.CoachService;
import com.dtk.service.SeatService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author GIGABYTE
 */
@RestController
@RequestMapping("/api")
public class ApiCoachController {

    @Autowired
    private CoachService coachService;
    @Autowired
    private SeatService seatService;

    @GetMapping("/coaches")
    public ResponseEntity<List<Coach>> getCoaches() {
        return new ResponseEntity<>(this.coachService.getCoachs(null), HttpStatus.OK);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getSeats() {
        return new ResponseEntity<>(this.seatService.getSeats(null), HttpStatus.OK);
    }

    @GetMapping("/seats/{idCoach}")
    public ResponseEntity<List<Seat>> getSeatsByIDCoach(@PathVariable(value = "idCoach") int idCoach) {
        return new ResponseEntity<>(this.seatService.getSeatsByIDCoach(idCoach), HttpStatus.OK);
    }

    @GetMapping("/coaches/getCoach/{idCoach}")
    public ResponseEntity<List<Coach>> getCoachByID(@PathVariable(value = "idCoach") int idCoach) {
        List<Coach> coach = new ArrayList<>();
        coach.add(this.coachService.getCoachByID(idCoach));

        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @PutMapping("/coaches/editCoach")
    public boolean editCoach(@RequestBody Coach coach, HttpSession session) {
        try {
            this.coachService.editCoach(coach);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @DeleteMapping("/coaches/deleteCoach/{coachId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoach(@PathVariable(value = "coachId") int id) {
        this.coachService.deleteCoach(id);
    }
}
