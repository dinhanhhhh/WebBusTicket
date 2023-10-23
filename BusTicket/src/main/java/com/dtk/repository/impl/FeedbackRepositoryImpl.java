/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.Feedback;
import com.dtk.pojo.Trip;
import com.dtk.pojo.User;
import com.dtk.repository.FeedbackRepository;
import com.dtk.repository.UserRepository;
import com.dtk.service.TripService;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class FeedbackRepositoryImpl implements FeedbackRepository {
    
    @Autowired
    private UserRepository userRepsitory;
    
    @Autowired
    private TripService tripService;

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Feedback> getFeedbacks(int idChuyenDi) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Feedback> q =b.createQuery(Feedback.class);
        Root<Feedback> root = q.from(Feedback.class);
        q.select(root);
        q.where(b.equal(root.get("idTrip").get("id"), idChuyenDi));
        q.orderBy(b.desc(root.get("createdDate")));
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Feedback addFeedback(String comment, int idChuyenDi) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Feedback b = new Feedback();
        b.setComment(comment);
        b.setIdTrip(this.tripService.getTripById(idChuyenDi));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        b.setIdCustomer(this.userRepsitory.getUserByUsername(authentication.getName().toString()));
        session.save(b);
        return b;
    }

}
