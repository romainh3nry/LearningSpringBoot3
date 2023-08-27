package com.springbootlearning.learningspringboot3.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserAccount {
    
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    protected UserAccount() {}

    public UserAccount(String username, String password, String... authorities) {
    this.username = username;
    this.password = password;
    this.authorities = Arrays.stream(authorities) //
      .map(SimpleGrantedAuthority::new) //
      .map(GrantedAuthority.class::cast) //
      .toList();
  }

  public UserDetails asUser () {
    return User.withDefaultPasswordEncoder()
        .username(getUsername())
        .password(getPassword())
        .authorities(getAuthorities())
        .build();
  }
}
