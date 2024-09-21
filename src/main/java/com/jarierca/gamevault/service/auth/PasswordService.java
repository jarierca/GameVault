package com.jarierca.gamevault.service.auth;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
