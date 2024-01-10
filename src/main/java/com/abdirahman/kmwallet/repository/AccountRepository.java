package com.abdirahman.kmwallet.repository;

import com.abdirahman.kmwallet.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<List<Account>> findByCustomerId(String customerId);
}
