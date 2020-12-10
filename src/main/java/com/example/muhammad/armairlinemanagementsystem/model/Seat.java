package com.example.muhammad.armairlinemanagementsystem.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @OneToOne
    private  Flight flight;
    @NotNull
    private  int capacity;
    @NotNull
    private int economy, firstClass,business;

    protected Seat() {

    }

    public Seat(Flight flight, int capacity, int economy, int firstClass, int business) {
        this.flight = flight;
        this.capacity = capacity;
        this.economy = economy;
        this.firstClass = firstClass;
        this.business = business;
    }

    public int getId() {
        return id;
    }


    public Flight getFlight() {
        return flight;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEconomy() {
        return economy;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass = firstClass;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }
}
