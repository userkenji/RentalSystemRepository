package com.ikeda.service;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean login(String email, String password) {

        // ★今はダミーの固定値、後でDBと連携して書き換える
        if ("test@test.com".equals(email) && "pass123".equals(password)) {
            return true;
        }

        return false;
    }
}
