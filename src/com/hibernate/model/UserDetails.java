package com.hibernate.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Id;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

@Entity(name = "UserDetails")
@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
//    @OneToOne 
//    @JoinColumn(name="VEHICLE_ID")
//    @OneToMany(mappedBy="user")
//    @JoinTable(name="USER_VEHICLE", joinColumns=@JoinColumn(name="USER_ID"), 
//            inverseJoinColumns=@JoinColumn(name="vehicleId"))
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="user")
    private List<Vehicle> vehicle = new ArrayList<Vehicle>();

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
