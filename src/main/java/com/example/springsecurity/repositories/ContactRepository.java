package com.example.springsecurity.repositories;

import com.example.springsecurity.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
