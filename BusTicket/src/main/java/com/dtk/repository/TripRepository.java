/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtk.repository;

import com.dtk.pojo.Trip;
import java.util.List;
import java.util.Map;
import sun.jvm.hotspot.oops.ObjArray;

/**
 *
 * @author XGEAR
 */
public interface TripRepository {
    List<Trip> getTrips(String kw, int page);
    List<Trip> getTripsAPI(Map<String, String> params, int page);
    Trip getTripById(int id);
    int countTrip();
    boolean addTrip(Trip trip);
    boolean deleteTrip(int id);
    boolean editTrip(Trip trip);
    Trip getTripByID(String id);
    List<Object[]> routeStats();
}
