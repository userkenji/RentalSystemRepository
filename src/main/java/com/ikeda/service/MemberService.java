package com.ikeda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Service
public class MemberService {
	 
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 正しい保存処理
    public void saveMember(Member member) {

        // ① パスワードをハッシュ化
        String hashed = passwordEncoder.encode(member.getPassword());
        member.setPassword(hashed);

        // ② DBへ保存
        memberRepository.save(member);
        System.out.println("★ DB保存した id=" + member.getId());
    }
}
