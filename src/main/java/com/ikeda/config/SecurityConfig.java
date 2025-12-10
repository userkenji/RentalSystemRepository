package com.ikeda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Configuration
public class SecurityConfig {
	@Autowired
    private MemberRepository memberRepository;

    // ★★★ ここを追加（UserDetailsService を設定）★★★
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Member member = memberRepository.findByEmail(email);

            if (member == null) {
                throw new UsernameNotFoundException("User not found");
            }

            // ★ role カラムが無いので固定で USER を付与
            return org.springframework.security.core.userdetails.User.builder()
                    .username(member.getEmail())      // ← ログインID
                    .password(member.getPassword())    // ← ハッシュ済パスワード
                    .roles("USER")
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/form","/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")     // ★ ログインIDに email を使用
                .passwordParameter("password")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
