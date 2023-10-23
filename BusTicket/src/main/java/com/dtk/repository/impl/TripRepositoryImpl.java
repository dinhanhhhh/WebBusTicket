/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.Route;
import com.dtk.pojo.Trip;
import com.dtk.repository.TripRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author XGEAR
 */
@Repository
@Transactional
@PropertySource("classpath:databases.properties")
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;

    @Override
    public List<Trip> getTrips(String kw, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Trip> query = b.createQuery(Trip.class);
        Root root = query.from(Trip.class);
        query.select(root);

//        if (kw != null && !kw.isEmpty()) {
//            Predicate p = b.like(root.get("name").as(String.class), String.format("%%s%%", kw));
//            
//            query =query.where(p);
        List<Predicate> predicates = new ArrayList<>();
//            String kw = params.get("kw");

        if (kw != null && !kw.isEmpty()) {
            Predicate p = b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
            predicates.add(p);
        }

        query.where(predicates.toArray(new Predicate[0]));
//        }
        query = query.orderBy(b.desc(root.get("id")));
        Query q = session.createQuery(query);

        int max = 6;
        q.setMaxResults(max);
        q.setFirstResult((page - 1) * max);

        return q.getResultList();
    }

    @Override
public int countTrip() {
    Session session = this.sessionFactory.getObject().getCurrentSession();
    Query q = session.createQuery("SELECT COUNT(*) FROM Trip");

    try {
        return Integer.parseInt(q.getSingleResult().toString());
    } catch (NoResultException e) {
        // Xử lý ngoại lệ khi không có kết quả
        return 0; // Hoặc giá trị mặc định tùy vào ngữ cảnh
    }
}


    @Override
    public Trip getTripById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Trip.class, id);
    }

    @Override
    public boolean addTrip(Trip trip) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(trip);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTrip(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Trip t = session.get(Trip.class, id);
            session.delete(t);

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Trip getTripByID(String id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Trip> q = b.createQuery(Trip.class);
        Root root = q.from(Trip.class);
        q.select(root);
        List<Predicate> predicates = new ArrayList<>();
        Predicate p1 = b.equal(root.get("id").as(String.class), id);
        predicates.add(p1);
        q.where(predicates.toArray(new Predicate[]{}));
        Query query = session.createQuery(q);
        return (Trip) query.getSingleResult();

    }

    @Override
    public boolean editTrip(Trip trip) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.update(trip);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Object[]> routeStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rT = q.from(Trip.class);
        Root rR = q.from(Route.class);
        q.where(b.equal(rT.get("idRoute"), rR.get("id")));
        q.multiselect(rR.get("id"), rR.get("start"), rR.get("end"), b.count(rT.get("id")));
        q.groupBy(rR.get("id"));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Trip> getTripsAPI(Map<String, String> params, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Trip> q = b.createQuery(Trip.class);
        Root root = q.from(Trip.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");

            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
                predicates.add(p);
            }

            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(new Predicate[0]));
            }

        }

        Query query = session.createQuery(q);

        if (page > 0) {
            int size = Integer.parseInt(env.getProperty("page.size").toString());
            int start = (page - 1) * size;
            query.setFirstResult(start);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

}
