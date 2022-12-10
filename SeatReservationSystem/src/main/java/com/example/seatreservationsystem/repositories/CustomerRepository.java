package com.example.seatreservationsystem.repositories;

import com.example.seatreservationsystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
