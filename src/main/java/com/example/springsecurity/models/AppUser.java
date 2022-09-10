package com.example.springsecurity.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
//    private String lastName;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.CUSTOMER;

    public AppUser(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }





//



}
