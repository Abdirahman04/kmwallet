package com.abdirahman.kmwallet.service;

import com.abdirahman.kmwallet.model.entity.CommunityMessage;
import com.abdirahman.kmwallet.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommunityService {
    CommunityRepository communityRepository;
    public CommunityService() {
    }
    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<CommunityMessage> getAllCommunityMessages() {
        return communityRepository.findAll();
    }

    public void addCommunityMessage(CommunityMessage communityMessage) {
        communityRepository.save(communityMessage);
    }
}
