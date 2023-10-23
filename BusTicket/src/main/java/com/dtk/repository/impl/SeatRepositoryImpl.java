/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.Seat;
import com.dtk.repository.SeatRepository;
import com.dtk.service.CoachService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GIGABYTE
 */
@Repository
@Transactional
public class SeatRepositoryImpl implements SeatRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private CoachService coachService;

    @Override
    public List<Seat> getSeats(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Seat> q = b.createQuery(Seat.class);
        Root root = q.from(Seat.class);
        q.select(root);

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean addSeat(int idCoach, int totalSeat) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Seat s = new Seat();
            for (int i = 1; i <= totalSeat; i++) {
                if (i < 10) {
                    s.setName("A0" + String.valueOf(i));
                } else {
                    s.setName("A" + String.valueOf(i));
                }
                s.setIdCoach(this.coachService.getCoachByID(idCoach));
                session.save(s);
                s = new Seat();
            }
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Seat> getSeatsByIDCoach(int idCoach) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Seat> q = b.createQuery(Seat.class);
        Root root = q.from(Seat.class);
        q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        Predicate p1 = b.equal(root.get("idCoach").get("id").as(Integer.class), idCoach);
        predicates.add(p1);
       q.where(predicates.toArray(new Predicate[0]));
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Seat getSeatByID(int idSeat) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Seat.class, idSeat);
    }

}
