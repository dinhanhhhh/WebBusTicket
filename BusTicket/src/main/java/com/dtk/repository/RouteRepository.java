/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository;

import com.dtk.pojo.Route;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface RouteRepository {
    List<Route> getRoutes(Map<String, String> params);
    Route getRouteByID(int id);
    boolean addRoute(Route route);
    boolean deleteRoute(int id);
    boolean editRoute(Route route);
}
