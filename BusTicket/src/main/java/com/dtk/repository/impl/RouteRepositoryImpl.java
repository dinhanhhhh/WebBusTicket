/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.Route;
import com.dtk.repository.RouteRepository;
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
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Route> getRoutes(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Route> q = b.createQuery(Route.class);
        Root root = q.from(Route.class);
        q.select(root);
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean addRoute(Route route) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(route);

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteRoute(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Route r = session.get(Route.class, id);
            session.delete(r);

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Route getRouteByID(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Route.class, id);
    }

    @Override
    public boolean editRoute(Route route) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.update(route);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

}
