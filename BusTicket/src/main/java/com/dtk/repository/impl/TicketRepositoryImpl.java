/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.repository.impl;

import com.dtk.pojo.Seat;
import com.dtk.pojo.Ticket;
import com.dtk.pojo.TicketDetail;
import com.dtk.pojo.User;
import com.dtk.repository.TicketRepository;
import com.dtk.service.TripService;
import com.dtk.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GIGABYTE
 */
@Repository
@Transactional
public class TicketRepositoryImpl implements TicketRepository {
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;
    
    @Override
    public List<TicketDetail> getTickets(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<TicketDetail> q = b.createQuery(TicketDetail.class);
        Root root = q.from(TicketDetail.class);
        q.select(root);
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Ticket> getTicketBook(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> q = b.createQuery(Ticket.class);
        Root root = q.from(Ticket.class);
        q.select(root);
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public User addUserCustomer(String fullname, String gender, String address, String phone, String email) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            User u = new User();
            
            u.setFullname(fullname);
            u.setGender(gender);
            u.setAddress(address);
            u.setPhone(phone);
            u.setEmail(email);
            
            session.save(u);
            
            return u;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Ticket addTicket(Map<String, String> params, int idTrip) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = formatter.parse(String.valueOf(java.time.LocalDateTime.now()));
            
            String fullname = params.get("fullname");
            String gender = params.get("gender");
            String address = params.get("address");
            String phone = params.get("phone");
            String email = params.get("email");
            User uCustomer = this.addUserCustomer(fullname, gender, address, phone, email);
            session.save(uCustomer);
            
            Ticket t = new Ticket();
            
            t.setIdCustomerNew(uCustomer);
            
            if (params.get("idCustomerLogin") == null) {
                t.setIdUserLogin(null);
            } else {
                User uUserLogin = this.userService.getUserById(Integer.parseInt(params.get("idCustomerLogin")));
                t.setIdUserLogin(uUserLogin);
            }
            
            t.setIdTrip(this.tripService.getTripById(idTrip));
            t.setBookDate(date);
            t.setPaymentMethod(params.get("paymentMethod"));
            
            if (params.get("paymentMethod").equals(Ticket.TrucTiep)) {
                t.setStatusTicket("Chưa thanh toán");
            } else {
                t.setPaymentDate(date);
                t.setStatusTicket("Ðã thanh toán");
            }
            
            session.save(t);
            return t;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(TicketRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public TicketDetail addSeatTicketDetail(Ticket ticket, Seat seat) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            TicketDetail tDetail = new TicketDetail();
            
            tDetail.setIdTicket(ticket);
            tDetail.setIdSeat(seat);
            tDetail.setPriceSeat(ticket.getIdTrip().getPrice());
            tDetail.setStatusSeat(Boolean.TRUE);
            session.save(tDetail);
            return tDetail;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Ticket getTicketBookByID(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Ticket.class, id);
    }
    
    @Override
    public List<TicketDetail> getTicketDeatilByIDTrip(int idTrip) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<TicketDetail> q = b.createQuery(TicketDetail.class);
        Root root = q.from(TicketDetail.class);
        q.select(root);
        
        q.where(b.equal(root.get("idTicket").get("idTrip"), idTrip));
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<TicketDetail> getTicketDetailByIDTicket(int idTicket) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<TicketDetail> q = b.createQuery(TicketDetail.class);
        Root root = q.from(TicketDetail.class);
        q.select(root);
        
        q.where(b.equal(root.get("idTicket").get("id"), idTicket));
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<TicketDetail> getTicketDetailByIDUserLogin(int idUser) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<TicketDetail> q = b.createQuery(TicketDetail.class);
        Root root = q.from(TicketDetail.class);
        q.select(root);
        
        q.where(b.equal(root.get("idTicket").get("idUserLogin"), idUser));
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public List<Ticket> getTicketBookByIDUser(int idUser) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> q = b.createQuery(Ticket.class);
        Root root = q.from(Ticket.class);
        q.select(root);
        
        q.where(b.equal(root.get("idUserLogin"), idUser));
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public boolean xacNhanTicket(int idTicket) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = formatter.parse(String.valueOf(java.time.LocalDateTime.now()));
            
            Ticket ticketB = session.get(Ticket.class, idTicket);
            ticketB.setPaymentDate(date);
            ticketB.setStatusTicket("Ðã thanh toán");
            session.update(ticketB);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(TicketRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public boolean deleteTicket(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            Ticket t = session.get(Ticket.class, id);
            session.delete(t);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
    
}
