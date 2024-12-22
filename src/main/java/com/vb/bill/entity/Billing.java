package com.vb.bill.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "Billings")
@Data
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bId;

    private String bName;

    private String bCity;

    private String bStreet;

    private LocalDateTime createdOn = LocalDateTime.now();

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getbCity() {
        return bCity;
    }

    public void setbCity(String bCity) {
        this.bCity = bCity;
    }

    public String getbStreet() {
        return bStreet;
    }

    public void setbStreet(String bStreet) {
        this.bStreet = bStreet;
    }
}
