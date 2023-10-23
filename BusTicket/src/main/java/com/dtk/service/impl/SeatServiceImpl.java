/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.service.impl;

import com.dtk.pojo.Seat;
import com.dtk.repository.SeatRepository;
import com.dtk.service.SeatService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GIGABYTE
 */
@Service
public class SeatServiceImpl implements SeatService{

    @Autowired
    private SeatRepository seatRepository;
    
    @Override
    public List<Seat> getSeats(Map<String, String> params) {
        return this.seatRepository.getSeats(params);
    }

    @Override
    public boolean addSeat(int idCoach, int totalSeat) {
        return this.seatRepository.addSeat(idCoach, totalSeat);
    }

    @Override
    public List<Seat> getSeatsByIDCoach(int idCoach) {
        return this.seatRepository.getSeatsByIDCoach(idCoach);
    }

    @Override
    public Seat getSeatByID(int idSeat) {
        return this.seatRepository.getSeatByID(idSeat);
    }
    
}
