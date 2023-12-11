package com.bibliotecaBE.data.entity;

import com.bibliotecaBE.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Utente")
public class Utente implements UserDetails {

    //****Sezione attributi****

    @Id
    @Column(name="IDUtente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQCHIAVEUTENTE")
    @SequenceGenerator(name="SEQCHIAVEUTENTE", sequenceName = "SEQCHIAVEUTENTE", allocationSize = 1)
    private Integer id;

    @Column(name="Nome")
    private String nome;

    @Column(name="Cognome")
    private String cognome;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="Ruolo")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
