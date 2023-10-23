/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author GIGABYTE
 */
@Entity
@Table(name = "trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findById", query = "SELECT t FROM Trip t WHERE t.id = :id"),
    @NamedQuery(name = "Trip.findByName", query = "SELECT t FROM Trip t WHERE t.name = :name"),
    @NamedQuery(name = "Trip.findByImage", query = "SELECT t FROM Trip t WHERE t.image = :image"),
    @NamedQuery(name = "Trip.findByStartTime", query = "SELECT t FROM Trip t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "Trip.findByPrice", query = "SELECT t FROM Trip t WHERE t.price = :price")})
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    @NotNull(message = "{trip.name.err}")
    private String name;
    @Size(max = 150)
    @Column(name = "image")
    private String image;
    @Column(name = "start_time")
    @NotNull(message = "{trip.startTime.err}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull(message = "{trip.price.err}")
    @Column(name = "price")
    private long price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrip")
    @JsonIgnore
    private Set<Feedback> feedbackSet;
    @JoinColumn(name = "id_coach", referencedColumnName = "id")
    @ManyToOne(optional = false)
//    @JsonIgnore
    private Coach idCoach;
    @JoinColumn(name = "id_route", referencedColumnName = "id")
    @ManyToOne(optional = false)
//    @JsonIgnore
    private Route idRoute;
    @JoinColumn(name = "id_driver", referencedColumnName = "id")
    @ManyToOne(optional = false)
//    @JsonIgnore
    private User idDriver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrip")
    @JsonIgnore
    private Set<Ticket> ticketSet;

    public Trip() {
    }

    public Trip(Integer id) {
        this.id = id;
    }

    public Trip(Integer id, long price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @XmlTransient
    public Set<Feedback> getFeedbackSet() {
        return feedbackSet;
    }

    public void setFeedbackSet(Set<Feedback> feedbackSet) {
        this.feedbackSet = feedbackSet;
    }

    public Coach getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Coach idCoach) {
        this.idCoach = idCoach;
    }

    public Route getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Route idRoute) {
        this.idRoute = idRoute;
    }

    public User getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(User idDriver) {
        this.idDriver = idDriver;
    }

    @XmlTransient
    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
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
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtk.pojo.Trip[ id=" + id + " ]";
    }
    
}
