package com.build.twitter_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity(name = "User")
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username ;

    @NotNull
    private String password;

    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @Column(name="updated_at", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        var current_timestamp = new Date();

        createdAt = current_timestamp;
        updatedAt = current_timestamp;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    @Override
    public String getUsername() { return username; }

    @Override
    public String getPassword() { return password; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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