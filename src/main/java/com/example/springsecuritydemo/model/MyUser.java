package com.example.springsecuritydemo.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 130)
    private String username;

    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    public MyUser(String username, String password, Roles roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String encryptpasswd){
        this.password = encryptpasswd;
    }
    public Roles getRole(){
        return roles;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
