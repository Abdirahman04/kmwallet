package com.abdirahman.kmwallet.repository;

import com.abdirahman.kmwallet.model.entity.CommunityMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityMessage, Long> {
}
