    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.service.impl;

import com.dtk.pojo.Feedback;
import com.dtk.pojo.Trip;
import com.dtk.repository.FeedbackRepository;
import com.dtk.repository.TripRepository;
import com.dtk.service.TripService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XGEAR
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Trip> getTrips(String kw, int page) {
        return this.tripRepository.getTrips(kw, page);
    }

    @Override
    public int countTrip() {
        return this.tripRepository.countTrip();
    }

    @Override
    public List<Feedback> getFeedbacks(int idChuyenDi) {
        return this.feedbackRepository.getFeedbacks(idChuyenDi);
    }

    @Override
    public Trip getTripById(int id) {
        return this.tripRepository.getTripById(id);
    }

    @Override
    public boolean addTrip(Trip trip) {
        trip.setImage("https://res.cloudinary.com/doe6rzwse/image/upload/v1661163654/busApp/hn-hcm_j9jqmm.jpg");
        return this.tripRepository.addTrip(trip);
    }

    @Override
    public boolean deleteTrip(int id) {
        return this.tripRepository.deleteTrip(id);
    }

    @Override
    public Feedback addFeedback(String comment, int idChuyenDi) {
        return this.feedbackRepository.addFeedback(comment, idChuyenDi);
    }

    @Override
    public Trip getTripByID(String id) {
        return this.tripRepository.getTripByID(id);
    }

    @Override
    public boolean editTrip(Trip trip) {
        trip.setImage("https://res.cloudinary.com/doe6rzwse/image/upload/v1661163654/busApp/hn-hcm_j9jqmm.jpg");
        return this.tripRepository.editTrip(trip);
    }

    @Override
    public List<Object[]> routeStats() {
       return this.tripRepository.routeStats();
    }

    @Override
    public List<Trip> getTripsAPI(Map<String, String> params, int page) {
        return this.tripRepository.getTripsAPI(params, page);
    }

}
