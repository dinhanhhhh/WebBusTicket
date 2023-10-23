/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.service.impl;

import com.dtk.pojo.Coach;
import com.dtk.repository.CoachRepository;
import com.dtk.service.CoachService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public List<Coach> getCoachs(Map<String, String> params) {
        return this.coachRepository.getCoachs(params);
    }

    @Override
    public boolean addCoach(Coach coach) {
        return this.coachRepository.addCoach(coach);
    }

    @Override
    public boolean deleteCoach(int id) {
        return this.coachRepository.deleteCoach(id);
    }

    @Override
    public Coach getCoachByID(int id) {
        return this.coachRepository.getCoachByID(id);
    }

    @Override
    public boolean editCoach(Coach coach) {
        return this.coachRepository.editCoach(coach);
    }

}
