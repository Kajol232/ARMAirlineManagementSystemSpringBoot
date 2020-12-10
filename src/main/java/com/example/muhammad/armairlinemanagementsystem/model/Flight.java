package com.example.muhammad.armairlinemanagementsystem.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String departure, destination;
    @NotNull
    private Date departureDate;
    @NotNull
    private Date arrivalTime;
    @NotNull
    private int seat;
    @NotNull
    private double price;
    @NotNull
    @ManyToOne
    private  Aircraft aircraft;
    @NotNull
    @Column(unique = true, length = 20)
    private String flightRegNum;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    protected Flight() {
    }

    public Flight(String departure, String destination, Date departureDate, Date arrivalTime, int seat, double price,
                  Aircraft aircraft) {
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.seat = seat;
        this.price = price;
        this.aircraft = aircraft;
    }

    public Flight(String flightRegNum, String departure, String destination, Date departureDate, Date arrivalTime, int seat,
                  double price, Aircraft aircraft) {
        this.flightRegNum = flightRegNum;
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.seat = seat;
        this.price = price;
        this.aircraft = aircraft;
    }

    public int getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureTime) {
        this.departureDate = departureDate;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }


    public String getFlightRegNum() {
        return flightRegNum;
    }

    public void setFlightRegNum(String flightRegNum) {
        this.flightRegNum = flightRegNum;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
