package com.ikeda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 会員一覧を取得
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * ID を指定して会員を取得
     */
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    /**
     * 会員を削除
     */
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    /**
     * 会員情報を更新
     */
    public Member update(Long id, Member formUser) {
        Member member = findById(id);
        if (member == null) return null;

        // 必要な項目だけ更新する（例）
        member.setLastName(formUser.getLastName());
        member.setFirstName(formUser.getFirstName());
        member.setEmail(formUser.getEmail());
        member.setAddress(formUser.getAddress());

        return memberRepository.save(member);
	 
//    @Autowired
//    private MemberRepository memberRepository;

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
