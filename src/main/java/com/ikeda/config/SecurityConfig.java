package com.ikeda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ikeda.entity.Employee;
import com.ikeda.entity.Member;
import com.ikeda.repository.EmployeeRepository;
import com.ikeda.repository.MemberRepository;

@Configuration
public class SecurityConfig {
	@Autowired
    private MemberRepository memberRepository;

	@Autowired
    private EmployeeRepository employeeRepository;
    // --------------------------
	// 利用者用UserDetailService
	// --------------------------
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
        	 // ① 利用者（members）を検索
            Member member = memberRepository.findByEmail(email);
            if (member != null) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(member.getEmail())
                        .password(member.getPassword())
                        .roles("USER")  // 一般利用者
                        .build();
            }

            // ② 管理者（employees）を検索
            Employee employee = employeeRepository.findByEmail(email);
            if (employee != null) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(employee.getEmail())
                        .password(employee.getPassword())
                        .roles("ADMIN") // 管理者
                        .build();
            }

            // どちらにも見つからなければ例外
            throw new UsernameNotFoundException("User not found");
        };
    }
    

    @Bean
    @Order(1)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
          .securityMatcher("/admin/**")
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/","/admin/login", "/css/**", "/js/**", "/img/**").permitAll()
              .anyRequest().hasRole("ADMIN")
          )
          .formLogin(form -> form
              .loginPage("/admin/login")
              .loginProcessingUrl("/admin/login")
              .usernameParameter("email")
              .passwordParameter("password")
              .defaultSuccessUrl("/admin/index", true)
              .failureUrl("/admin/login?error")
          )
          .logout(logout -> logout
        		.logoutUrl("/admin/logout")
			    .logoutSuccessUrl("/?logout")
			    .invalidateHttpSession(true)
			    .deleteCookies("JSESSIONID")
			    .permitAll()
			);

        return http.build();
    }
    @Bean
    @org.springframework.core.annotation.Order(2)
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/", "/login", "/form", "/css/**", "/js/**", "/img/**", "/detail/**").permitAll()
              .anyRequest().authenticated()
          )
          .formLogin(form -> form
              .loginPage("/login")
              .loginProcessingUrl("/login")
              .usernameParameter("email")
              .passwordParameter("password")
              .defaultSuccessUrl("/home", true)
          )
          .logout(logout -> logout
			    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
			    .logoutSuccessUrl("/?logout")
			    .invalidateHttpSession(true)
			    .deleteCookies("JSESSIONID")
			    .permitAll()
			);


        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/login", "/form","/css/**", "/js/**","/img/**", "/detail/**").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .usernameParameter("email")     // ★ ログインIDに email を使用
//                .passwordParameter("password")
//                .successHandler((request, response, authentication) -> {
//
//                    boolean isAdmin = authentication.getAuthorities().stream()
//                            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//
//                    if (isAdmin) {
//                        response.sendRedirect("/admin/index");
//                    } else {
//                        response.sendRedirect("/home");
//                    }
//                })
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll()
//            );
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
