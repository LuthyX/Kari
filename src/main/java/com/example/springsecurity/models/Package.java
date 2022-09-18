package com.example.springsecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

@AllArgsConstructor
@NoArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customerid", insertable = false, updatable = false)
    private Customer customer;
    private Long customerid;

    private String trackcode;

    private Double weight;

    private Double fee;

    private Boolean paid = false;

    private LocalDate deliveryDate = null;

    @Enumerated(EnumType.STRING)
    private PackageStatus status = PackageStatus.PROCESSING;

    @Enumerated(EnumType.STRING)
    private DeliveryType delivery = DeliveryType.PICKUP;



    public Package(Customer customer, Long customerid, String trackcode, Double weight) {
        this.customer = customer;
        this.customerid = customerid;
        this.trackcode = trackcode;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getTrackcode() {
        return trackcode;
    }

    public void setTrackcode(String trackcode) {
        this.trackcode = trackcode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getFee() {
        Double finalFee;
        if(this.delivery == DeliveryType.HOME){
            finalFee = (this.weight*5)+10;
        }
        else{
            finalFee = this.weight*5;
        }
        return finalFee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }


    public DeliveryType getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryType delivery) {
        this.delivery = delivery;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
