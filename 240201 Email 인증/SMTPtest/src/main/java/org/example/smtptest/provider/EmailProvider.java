package org.example.smtptest.provider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProvider {
    private final JavaMailSender javaMailSender;
    private final String subject = "인증메일입니다.";
    public boolean sendCertificationMail(String email, String certificationNumber){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);              // 메일 제목
            messageHelper.setText(htmlContent, true);  // html을 넣을 거면 true

            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 메일 content를 html로 만들기
    private String getCertificationMessage(String certificationNumber){
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align:center;'>인증메일 코드입니다.</h1>";
        certificationMessage += "<h3 style='text-align:center;'>인증코드는 <strong style='font-size:32px;letter-spacing: 8px'>" + certificationNumber + "<strong></h3>";
        return certificationMessage;
    }
}
