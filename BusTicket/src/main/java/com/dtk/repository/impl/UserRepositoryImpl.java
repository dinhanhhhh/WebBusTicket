/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.User;
import com.dtk.repository.UserRepository;
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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GIGABYTE
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean addUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (user.getUsername() != null || user.getPassword() != null || user.getConfirmPassword() != null) {
                session.save(user);

                return true;
            }
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            User u = session.get(User.class, id);
            session.delete(u);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public User getUserByUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        q.where(b.equal(root.get("username").as(String.class), username.trim()));

        Query query = session.createQuery(q);
        return (User) query.getSingleResult();

    }

    @Override
    public List<User> getUsers(Map<String, String> params, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("fullname").as(String.class),
                        String.format("%%%s%%", kw));
                predicates.add(p);
            }

            q.where(predicates.toArray(new Predicate[]{}));
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

    @Override
    public List<User> getUserByRole(String userRole) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        q.where(b.equal(root.get("userRole"), userRole));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

//    @Override
//    public boolean editUser(User user) {
//        Session session = this.sessionFactory.getObject().getCurrentSession();
//        try {
//            session.update(user);
//
//            return true;
//        } catch (HibernateException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return false;
//    }
    @Override
    public boolean editUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            User u = session.get(User.class, user.getId());
            if (u != null) {
                u.setFullname(user.getFullname());
                u.setGender(user.getGender());
                u.setUserRole(user.getUserRole());
                u.setBirthday(user.getBirthday());
                u.setAddress(user.getAddress());
                u.setPhone(user.getPhone());
                u.setEmail(user.getEmail());
                u.setUsername(user.getUsername());

                if (!user.getPassword().isEmpty()) {
                    String encodedPassword = this.passwordEncoder.encode(user.getPassword());
                    u.setPassword(encodedPassword);
                }

                u.setAvatar(user.getAvatar());
                u.setActive(user.getActive());

                session.update(u);
                session.getTransaction().commit();
                return true;
            }
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public List<User> getUserByUsernameList(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query.select(root);
        query.where(builder.equal(root.get("username").as(String.class), username.trim()));

        Query q = session.createQuery(query);
        return q.getResultList();
    }

}
