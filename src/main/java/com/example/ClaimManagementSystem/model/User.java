package com.example.ClaimManagementSystem.model;

import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="\"user\"")
public class User implements UserDetails {
    @Id
    @Column(name="id", nullable=false, unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuid", nullable=false, unique=true)
    private String uuid;

    @Basic
    @Column(name="user_firstname", length=50, nullable=false)
    private String userFirstName;

    @Basic
    @Column(name="user_lastname", length=50, nullable=false)
    private String userLastName;

    @Basic
    @Column(name="username", length=50, nullable=false, unique=true)
    private String userName;

    @Basic
    @Column(length=255, nullable=false)
    private String password;

    @Basic
    @Column(length=50, nullable=false, unique=true)
    private String email;


    public User(String firstname, String lastname, String username, String email, String password){
        this.userFirstName=firstname;
        this.userLastName=lastname;
        this.userName=username;
        this.email=email;
        this.password=password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
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

    public String getUserName() {
        return userName;
    }
}