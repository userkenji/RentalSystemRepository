package com.ikeda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Service
public class RegisterService {

    @Autowired
    private MemberRepository memberRepository;

   private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 新規登録
     */
    public Member register(Member member) {

        // パスワードを暗号化
        String hash = passwordEncoder.encode(member.getPassword());
        member.setPassword(hash);

        return memberRepository.save(member);
    }

    public boolean existsByUsername(String username) {
        return memberRepository.findByUsername(username) != null;
    }

    public boolean existsByEmail(String email) {
        return memberRepository.findByEmail(email) != null;
    }
}
