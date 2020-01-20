package com.example.subiect2e;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity (tableName = "serviceCards")
public class ServiceCard implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int serviceNumber;
    private String serviceDepartment;
    private double serviceCost;
    private Date serviceDate;

    public ServiceCard() {
    }

    public ServiceCard(int serviceNumber, String serviceDepartment, double serviceCost, Date serviceDate) {
        this.serviceNumber = serviceNumber;
        this.serviceDepartment = serviceDepartment;
        this.serviceCost = serviceCost;
        this.serviceDate = serviceDate;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getServiceDepartment() {
        return serviceDepartment;
    }

    public void setServiceDepartment(String serviceDepartment) {
        this.serviceDepartment = serviceDepartment;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    @Override
    public String toString() {
        return "ServiceCard{" +
                "serviceNumber=" + serviceNumber +
                ", serviceDepartment=" + serviceDepartment +
                ", serviceCost=" + serviceCost +
                ", serviceDate=" + serviceDate +
                '}';
    }
}
