/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.service.impl;

import com.dtk.pojo.Route;
import com.dtk.repository.RouteRepository;
import com.dtk.service.RouteService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> getRoutes(Map<String, String> params) {
        return this.routeRepository.getRoutes(params);
    }

    @Override
    public boolean addRoute(Route route) {
        return this.routeRepository.addRoute(route);
    }

    @Override
    public boolean deleteRoute(int id) {
        return this.routeRepository.deleteRoute(id);
    }

    @Override
    public Route getRouteByID(int id) {
        return this.routeRepository.getRouteByID(id);
    }

    @Override
    public boolean editRoute(Route route) {
        return this.routeRepository.editRoute(route);
    }

}
