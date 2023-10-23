/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Feedback;
import com.dtk.pojo.User;
import com.dtk.service.TripService;
import com.dtk.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiFeedbackController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @GetMapping("/trips/{idChuyenDi}/feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbacks(@PathVariable(value = "idChuyenDi") int idChuyenDi) {
        return new ResponseEntity<>(this.tripService.getFeedbacks(idChuyenDi), HttpStatus.OK);
    }

    @PostMapping(path = "trips/{idChuyenDi}/feedbacks", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Feedback> addFeedback(@RequestBody Map<String, String> params) {
        String comment = params.get("comment");
        int idChuyenDi = Integer.parseInt(params.get("idTrip"));
        Feedback b = this.tripService.addFeedback(comment, idChuyenDi);
        return new ResponseEntity<>(b, HttpStatus.CREATED);
    }

}
