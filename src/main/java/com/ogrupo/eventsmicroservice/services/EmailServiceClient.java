package com.ogrupo.eventsmicroservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ogrupo.eventsmicroservice.dtos.EmailRequestDTO;

@FeignClient(name = "email-service", url = "http://email-service:8090")
public interface EmailServiceClient {
    @PostMapping("/api/email")
    void sendEmail(@RequestBody EmailRequestDTO emailRequest);
}
