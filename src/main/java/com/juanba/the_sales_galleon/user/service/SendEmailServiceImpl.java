package com.juanba.the_sales_galleon.user.service;

import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl {

    public void sendPasswordResetEmail(String email, String verificationCode) {
        System.out.println("Email: " + email);
        System.out.println("Codigo: " + verificationCode);
        System.out.println("Tiempo se expiracion: 20min");
    }
}
