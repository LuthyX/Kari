package com.example.springsecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "appuserid", insertable = false, updatable = false)
    private AppUser appUser;
    private Long appuserid;

    private double wallet= 100.0;

    private String phoneNumber;

    private LocalDate dob;

    private String sex;

    public Customer(AppUser appUser, Long appuserid) {
        this.appUser = appUser;
        this.appuserid = appuserid;

    }

}

