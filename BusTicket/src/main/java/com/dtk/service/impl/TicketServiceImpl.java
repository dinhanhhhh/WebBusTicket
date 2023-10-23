/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.service.impl;

import com.dtk.pojo.Seat;
import com.dtk.pojo.Ticket;
import com.dtk.pojo.TicketDetail;
import com.dtk.pojo.User;
import com.dtk.repository.TicketRepository;
import com.dtk.service.TicketService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GIGABYTE
 */
@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<TicketDetail> getTickets(Map<String, String> params) {
        return this.ticketRepository.getTickets(params);
    }

    @Override
    public List<Ticket> getTicketBook(Map<String, String> params) {
        return this.ticketRepository.getTicketBook(params);
    }

    @Override
    public User addUserCustomer(String fullname, String gender, String address, String phone, String email) {
        return this.ticketRepository.addUserCustomer(fullname, gender, address, phone, email);
    }

    @Override
    public Ticket addTicket(Map<String, String> params, int idTrip) {
        return this.ticketRepository.addTicket(params, idTrip);
    }

    @Override
    public TicketDetail addSeatTicketDetail(Ticket ticket, Seat seat) {
        return this.ticketRepository.addSeatTicketDetail(ticket, seat);
    }

    @Override
    public Ticket getTicketBookByID(int id) {
        return this.ticketRepository.getTicketBookByID(id);
    }

    @Override
    public List<TicketDetail> getTicketDetailByIDTrip(int idTrip) {
        return this.ticketRepository.getTicketDeatilByIDTrip(idTrip);
    }

    @Override
    public List<TicketDetail> getTicketDetailByIDTicket(int idTicket) {
        return this.ticketRepository.getTicketDetailByIDTicket(idTicket);
    }

    @Override
    public List<TicketDetail> getTicketDetailByIDUserLogin(int idUser) {
        return this.ticketRepository.getTicketDetailByIDUserLogin(idUser);
    }

    @Override
    public List<Ticket> getTicketBookByIDUser(int idUser) {
        return this.ticketRepository.getTicketBookByIDUser(idUser);
    }

    @Override
    public boolean xacNhanTicket(int idTicket) {
        return this.ticketRepository.xacNhanTicket(idTicket);
    }
    @Override
    public boolean deleteTicket(int id) {
        return this.ticketRepository.deleteTicket(id);
    }
}
