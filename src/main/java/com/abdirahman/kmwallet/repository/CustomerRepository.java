package com.abdirahman.kmwallet.repository;

import com.abdirahman.kmwallet.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
