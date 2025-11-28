package com.ikeda.service;

public interface LoginService {

    /**
     * ログイン処理
     * @param email メールアドレス
     * @param password パスワード
     * @return 成功なら true、失敗なら false
     */
    boolean login(String email, String password);
}

