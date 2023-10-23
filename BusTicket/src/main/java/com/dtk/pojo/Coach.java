/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GIGABYTE
 */
@Entity
@Table(name = "coach")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coach.findAll", query = "SELECT c FROM Coach c"),
    @NamedQuery(name = "Coach.findById", query = "SELECT c FROM Coach c WHERE c.id = :id"),
    @NamedQuery(name = "Coach.findByName", query = "SELECT c FROM Coach c WHERE c.name = :name"),
    @NamedQuery(name = "Coach.findByTotalseat", query = "SELECT c FROM Coach c WHERE c.totalseat = :totalseat"),
    @NamedQuery(name = "Coach.findByLicensePlates", query = "SELECT c FROM Coach c WHERE c.licensePlates = :licensePlates")})
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{coach.name.err}")
    @Size(min = 1, max = 45, message = "{coach.name.err}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull(message = "{coach.totalseat.nullErr}")
    @Min(value = 4, message = "{coach.totalseat.minErr}")
    @Max(value = 50, message = "{coach.totalseat.maxErr}")
    @Column(name = "totalseat")
    private int totalseat;
    @Basic(optional = false)
    @NotNull(message = "{coach.licensePlates.err}")
    @Size(min = 1, max = 45, message = "{coach.licensePlates.err}")
    @Column(name = "license_plates")
    private String licensePlates;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCoach")
    @JsonIgnore
    private Set<Seat> seatSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCoach")
    @JsonIgnore
    private Set<Trip> tripSet;

    public Coach() {
    }

    public Coach(Integer id) {
        this.id = id;
    }

    public Coach(Integer id, String name, int totalseat, String licensePlates) {
        this.id = id;
        this.name = name;
        this.totalseat = totalseat;
        this.licensePlates = licensePlates;
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

    public int getTotalseat() {
        return totalseat;
    }

    public void setTotalseat(int totalseat) {
        this.totalseat = totalseat;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    @XmlTransient
    public Set<Seat> getSeatSet() {
        return seatSet;
    }

    public void setSeatSet(Set<Seat> seatSet) {
        this.seatSet = seatSet;
    }

    @XmlTransient
    public Set<Trip> getTripSet() {
        return tripSet;
    }

    public void setTripSet(Set<Trip> tripSet) {
        this.tripSet = tripSet;
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
        if (!(object instanceof Coach)) {
            return false;
        }
        Coach other = (Coach) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.dtk.pojo.Coach[ id=" + id + " ]";
    }
    
}
