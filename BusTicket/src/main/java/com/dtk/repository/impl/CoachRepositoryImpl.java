/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.Coach;
import com.dtk.repository.CoachRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class CoachRepositoryImpl implements CoachRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Coach> getCoachs(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Coach> q = b.createQuery(Coach.class);
        Root root = q.from(Coach.class);
        q.select(root);

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean addCoach(Coach coach) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(coach);

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCoach(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            Coach c = session.get(Coach.class, id);
            session.delete(c);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Coach getCoachByID(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Coach.class, id);
    }

    @Override
    public boolean editCoach(Coach coach) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.update(coach);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

}
