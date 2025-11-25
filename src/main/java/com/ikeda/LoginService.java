package com.ikeda;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    // 仮のユーザー情報（ダミー用）
    private final String DUMMY_EMAIL = "test@example.com";
    private final String DUMMY_PASSWORD = "password";

    // ログインチェック用メソッド
    public boolean loginCheck(String email, String password) {
        // 仮に1件のユーザー情報と照合するだけ
        return DUMMY_EMAIL.equals(email) && DUMMY_PASSWORD.equals(password);
    }
}
