package com.abdirahman.kmwallet.controller;

import com.abdirahman.kmwallet.model.entity.CommunityMessage;
import com.abdirahman.kmwallet.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kmwallet")
public class CommunityController {
    CommunityService communityService;
    public CommunityController() {
    }
    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/community")
    public ResponseEntity<List<CommunityMessage>> getAllCommunityMessages() {
        List<CommunityMessage> communityMessageList = communityService.getAllCommunityMessages();
        return new ResponseEntity<>(communityMessageList, HttpStatus.OK);
    }

    @PostMapping("/community")
    public ResponseEntity<CommunityMessage> addMessage(@RequestBody CommunityMessage communityMessage) {
        CommunityMessage communityMessage1 = communityService.addCommunityMessage(communityMessage);
        return new ResponseEntity<>(communityMessage1, HttpStatus.OK);
    }
}
