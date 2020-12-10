package com.example.muhammad.armairlinemanagementsystem.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private boolean status;
    @NotNull
    @Column(unique = true,length = 10)
    private String aircraftModel;
    @NotNull
    private  int capacity;
    @NotNull

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    protected Aircraft() {
    }

    public Aircraft(String name, String aircraftModel, int capacity) {
        this.name = name;
        this.aircraftModel = aircraftModel;
        this.capacity = capacity;
    }

    public Aircraft(String name, boolean status, String aircraftModel, int capacity) {
        this.name = name;
        this.status = status;
        this.aircraftModel = aircraftModel;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String model) {
        this.aircraftModel = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
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
