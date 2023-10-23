/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.controllers;

import com.dtk.pojo.Route;
import com.dtk.service.RouteService;
import java.util.ArrayList;
import java.util.List;
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
public class ApiRouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getRoutes() {
        return new ResponseEntity<>(this.routeService.getRoutes(null), HttpStatus.OK);
    }
    
    @GetMapping("/routes/getRoute/{idRoute}")
    public ResponseEntity<List<Route>> getRouteByID(@PathVariable(value = "idRoute") int idRoute) {
        List<Route> route = new ArrayList<>();
        route.add(this.routeService.getRouteByID(idRoute));
        
        return new ResponseEntity<>(route, HttpStatus.OK);
    }
    
    @PutMapping("/routes/editRoute")
    public boolean editCoach(@RequestBody Route route) {
        try {
            this.routeService.editRoute(route);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @DeleteMapping("/routes/deleteRoute/{routeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable(value = "routeId") int id) {
        this.routeService.deleteRoute(id);
    }
}
