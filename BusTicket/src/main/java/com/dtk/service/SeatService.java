/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtk.service;

import com.dtk.pojo.Seat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GIGABYTE
 */
public interface SeatService {
    List<Seat> getSeats(Map<String, String> params);
    List<Seat> getSeatsByIDCoach(int idCoach);
    Seat getSeatByID(int idSeat);
    boolean addSeat(int idCoach, int totalSeat);
}
