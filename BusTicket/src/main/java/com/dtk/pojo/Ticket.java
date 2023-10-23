/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GIGABYTE
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
    @NamedQuery(name = "Ticket.findByBookDate", query = "SELECT t FROM Ticket t WHERE t.bookDate = :bookDate"),
    @NamedQuery(name = "Ticket.findByPaymentMethod", query = "SELECT t FROM Ticket t WHERE t.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "Ticket.findByPaymentDate", query = "SELECT t FROM Ticket t WHERE t.paymentDate = :paymentDate"),
    @NamedQuery(name = "Ticket.findByPaymentContent", query = "SELECT t FROM Ticket t WHERE t.paymentContent = :paymentContent"),
    @NamedQuery(name = "Ticket.findByStatusTicket", query = "SELECT t FROM Ticket t WHERE t.statusTicket = :statusTicket")})
public class Ticket implements Serializable {
    
    public static final String TrucTiep = "Trực tiếp";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "book_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookDate;
    @Size(max = 45)
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Size(max = 250)
    @Column(name = "payment_content")
    private String paymentContent;
    @Size(max = 45)
    @Column(name = "status_ticket")
    private String statusTicket;
    @JoinColumn(name = "id_trip", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Trip idTrip;
    @JoinColumn(name = "id_customer_new", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User idCustomerNew;
    @JoinColumn(name = "id_user_login", referencedColumnName = "id")
    @ManyToOne
    private User idUserLogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTicket")
    @JsonIgnore
    private Set<TicketDetail> ticketDetailSet;

    public Ticket() {
    }

    public Ticket(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentContent() {
        return paymentContent;
    }

    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent;
    }

    public String getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(String statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Trip getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Trip idTrip) {
        this.idTrip = idTrip;
    }

    public User getIdCustomerNew() {
        return idCustomerNew;
    }

    public void setIdCustomerNew(User idCustomerNew) {
        this.idCustomerNew = idCustomerNew;
    }

    public User getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(User idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    @XmlTransient
    public Set<TicketDetail> getTicketDetailSet() {
        return ticketDetailSet;
    }

    public void setTicketDetailSet(Set<TicketDetail> ticketDetailSet) {
        this.ticketDetailSet = ticketDetailSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtk.pojo.Ticket[ id=" + id + " ]";
    }
    
}
