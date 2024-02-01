package org.example.smtptest.controller;

import lombok.RequiredArgsConstructor;
import org.example.smtptest.dto.EmailRequest;
import org.example.smtptest.provider.EmailProvider;
import org.example.smtptest.util.CertificationNumber;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailProvider emailProvider;
    @PostMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestBody EmailRequest emailRequest){
        System.out.println(emailRequest);
        String authCode = CertificationNumber.getCertificaionNumber();
        System.out.println(authCode);
        boolean isSuccess = emailProvider.sendCertificationMail(emailRequest.getEmail(),authCode);
        if(!isSuccess) return ResponseEntity.ok("인증 실패");
        return ResponseEntity.ok("인증");
    }
}
