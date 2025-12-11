package com.ikeda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Service
public class LoginService {

    @Autowired
    private MemberRepository memberRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * メールアドレスとパスワードでログインチェック
     */
    public Member login(String email, String rawPassword) {

        // email で会員を検索
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            return null; // メールアドレスが存在しない
        }

        // パスワード一致チェック（ハッシュ照合）
        if (encoder.matches(rawPassword, member.getPassword())) {
            return member; // ログイン成功 → Member を返す
        }

        return null; // パスワード不一致
    }
}
