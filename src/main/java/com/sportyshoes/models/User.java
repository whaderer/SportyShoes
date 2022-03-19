package com.sportyshoes.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

// Implementations of UserDetails will provide some essential user information to
// the framework, such as what authorities are granted to the user and whether the user’s
// account is enabled.

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // ToDo validation
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final int age;
    private final String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @PrePersist
    private void onCreate() {
        dateAdded = new Date();
    }

    // The getAuthorities() method should return a collection of authorities granted
    // to the user. The various is* methods return a boolean to indicate whether the user’s
    // account is enabled, locked, or expired.
    // The User entity, the getAuthorities() method simply returns a collection
    // indicating that all users will have been granted ROLE_USER authority. And, at least for
    // now, there  is no need to disable users, so all of the is* methods return true to
    // indicate that the users are active.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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
