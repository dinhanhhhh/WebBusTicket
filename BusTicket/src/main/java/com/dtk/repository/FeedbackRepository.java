/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository;

import com.dtk.pojo.Feedback;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface FeedbackRepository {
    List<Feedback> getFeedbacks(int idChuyenDi);
 public Feedback addFeedback(String comment, int idChuyenDi);
}
