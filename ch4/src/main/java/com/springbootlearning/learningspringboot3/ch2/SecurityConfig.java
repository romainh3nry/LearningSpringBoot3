package com.springbootlearning.learningspringboot3.ch2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
  CommandLineRunner initUsers(UserManagementRepository repository) {
    return args -> {
      repository.save(new UserAccount("Alice", "password", "ROLE_USER"));
      repository.save(new UserAccount("Bob", "password", "ROLE_USER"));
      repository.save(new UserAccount("admin", "admin", "ROLE_ADMIN"));
    };
  }

  @Bean
  UserDetailsService userService(UserRepository repo) {
    return username->repo.findByUsername(username).asUser();
  }

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    .requestMatchers("/login").permitAll()
    .requestMatchers("/", "/search").authenticated()
    .requestMatchers(HttpMethod.POST, "/delete/**", "/new-video").authenticated() //
    .anyRequest()
    .denyAll()
    .and()
    .formLogin()
    .and()
    .httpBasic()
    .and()
    .csrf();
    return http.build();
  }
}
