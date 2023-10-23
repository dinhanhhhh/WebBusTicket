/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GIGABYTE
 */
@Entity
@Table(name = "ticket_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketDetail.findAll", query = "SELECT t FROM TicketDetail t"),
    @NamedQuery(name = "TicketDetail.findByIdTicketDetail", query = "SELECT t FROM TicketDetail t WHERE t.idTicketDetail = :idTicketDetail"),
    @NamedQuery(name = "TicketDetail.findByPriceSeat", query = "SELECT t FROM TicketDetail t WHERE t.priceSeat = :priceSeat"),
    @NamedQuery(name = "TicketDetail.findByStatusSeat", query = "SELECT t FROM TicketDetail t WHERE t.statusSeat = :statusSeat")})
public class TicketDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ticket_detail")
    private Integer idTicketDetail;
    @Column(name = "price_seat")
    private Long priceSeat;
    @Column(name = "status_seat")
    private Boolean statusSeat;
    @JoinColumn(name = "id_seat", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Seat idSeat;
    @JoinColumn(name = "id_ticket", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ticket idTicket;

    public TicketDetail() {
    }

    public TicketDetail(Integer idTicketDetail) {
        this.idTicketDetail = idTicketDetail;
    }

    public Integer getIdTicketDetail() {
        return idTicketDetail;
    }

    public void setIdTicketDetail(Integer idTicketDetail) {
        this.idTicketDetail = idTicketDetail;
    }

    public Long getPriceSeat() {
        return priceSeat;
    }

    public void setPriceSeat(Long priceSeat) {
        this.priceSeat = priceSeat;
    }

    public Boolean getStatusSeat() {
        return statusSeat;
    }

    public void setStatusSeat(Boolean statusSeat) {
        this.statusSeat = statusSeat;
    }

    public Seat getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Seat idSeat) {
        this.idSeat = idSeat;
    }

    public Ticket getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Ticket idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicketDetail != null ? idTicketDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketDetail)) {
            return false;
        }
        TicketDetail other = (TicketDetail) object;
        if ((this.idTicketDetail == null && other.idTicketDetail != null) || (this.idTicketDetail != null && !this.idTicketDetail.equals(other.idTicketDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtk.pojo.TicketDetail[ idTicketDetail=" + idTicketDetail + " ]";
    }
    
}
