/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository;

import com.dtk.pojo.Coach;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CoachRepository {

    List<Coach> getCoachs(Map<String, String> params);
    Coach getCoachByID(int id);
    boolean addCoach(Coach coach);
    boolean deleteCoach(int id);
    boolean editCoach(Coach coach);
}
