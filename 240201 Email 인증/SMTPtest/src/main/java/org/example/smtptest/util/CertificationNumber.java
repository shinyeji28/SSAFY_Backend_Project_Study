package org.example.smtptest.util;

public class CertificationNumber {
    public static String getCertificaionNumber(){
        String certificationNumber = "";
        for(int count = 0; count<6; count++) {
            certificationNumber += (int) (Math.random() * 10);
        }
        return certificationNumber;

    }
}
