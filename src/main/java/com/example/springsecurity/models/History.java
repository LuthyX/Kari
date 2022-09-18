package com.example.springsecurity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String info;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customerid", insertable = false, updatable = false)
    private Customer customer;
    private Long customerid;
    private LocalDateTime time;

    public History(String info, Long customerid, LocalDateTime time) {
        this.info = info;
        this.customerid = customerid;
        this.time = time;
    }


}
