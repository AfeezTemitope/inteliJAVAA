package com.example.contact.data.repository;

import com.example.contact.data.models.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contacts, Long> {

    List<Contacts> findByUserid(String userid);
}
