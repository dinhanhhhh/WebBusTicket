/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Coach;
import com.dtk.pojo.Route;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import com.dtk.service.CoachService;
import com.dtk.service.RouteService;
import com.dtk.service.TripService;
import com.dtk.service.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiTripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachService coachService;

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getTrips() {
        return new ResponseEntity<>(this.tripService.getTripsAPI(null, 0), HttpStatus.OK);
    }

    @GetMapping("/trips/getTrip/{idTrip}")
    public ResponseEntity<List<Trip>> getTripByID(@PathVariable(value = "idTrip") int idTrip) {
        List<Trip> trip = new ArrayList<>();
        trip.add(this.tripService.getTripById(idTrip));

        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

//    @PutMapping("/trips/editTrip")
//    public boolean editTrip(@RequestBody Trip trip) {
//        try {
//            this.tripService.editTrip(trip);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

    @PutMapping("/trips/editTrip")
    public boolean editTrip(@RequestBody Map<String, String> param) {
        try {
            Route r = this.routeService.getRouteByID(Integer.parseInt(param.get("idRoute")));
            User u = this.userService.getUserById(Integer.parseInt(param.get("idDriver")));
            Coach c = this.coachService.getCoachByID(Integer.parseInt(param.get("idCoach")));
            
            Trip trip = new Trip();
            trip.setId(Integer.parseInt(param.get("id")));
            trip.setName(param.get("name"));
            
            if (!param.get("startTime").isEmpty()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date date = formatter.parse(param.get("startTime"));
                trip.setStartTime(date);
            } else
                trip.setStartTime(null);
            
            trip.setPrice(Long.parseLong(param.get("price")));
            trip.setIdRoute(r);
            trip.setIdDriver(u);
            trip.setIdCoach(c);
            
            this.tripService.editTrip(trip);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    @DeleteMapping("/trips/deleteTrip/{tripId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable(value = "tripId") int idTrip) {
        this.tripService.deleteTrip(idTrip);
    }
}
