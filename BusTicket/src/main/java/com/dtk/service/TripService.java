/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.service;

import com.dtk.pojo.Feedback;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GIGABYTE
 */
public interface TripService {

    List<Trip> getTrips(String kw, int page);
    List<Trip> getTripsAPI(Map<String, String> params, int page);
    List<Feedback> getFeedbacks(int idChuyenDi);
    int countTrip();
    Trip getTripById(int id);
    boolean addTrip(Trip trip);
    boolean deleteTrip(int id);
    boolean editTrip(Trip trip);
    Feedback addFeedback(String comment, int idChuyenDi);
    Trip getTripByID(String id);
    List<Object[]> routeStats();
}
