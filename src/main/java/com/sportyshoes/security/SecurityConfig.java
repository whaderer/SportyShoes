package com.sportyshoes.security;

import com.sportyshoes.models.User;
import com.sportyshoes.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // declare a PasswordEncoder bean
    // the password in the database is never decoded. Instead, the password that the user enters
    // at login is encoded using the same algorithm, and it’s then compared with the encoded
    // password in the database. That comparison is performed in the PasswordEncoder’s matches() method.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // To configure a user store for authentication purposes, you’ll need to declare a
    // UserDetailsService bean. The UserDetailsService interface is relatively simple,
    // including only one method that must be implemented: The loadUserByUsername() method accepts a username
    // and uses it to look up a UserDetails object.
    // If no user can be found for the given username, then it will throw a UsernameNotFoundException.
    // Spring Security offers several out-of-the-box implementations of UserDetailsService
    // the UserDetailsService interface defines only a single loadUserBy-Username() method.
    // That means it is a functional interface and can be implemented
    // as a lambda instead of as a full-blown implementation class. Because all we really need
    // is for our custom UserDetailsService to delegate to the UserRepository, it can be
    // simply declared as a bean using the following configuration method.
    // The userDetailsService() method is given a UserRepository as a parameter. To
    // create the bean, it returns a lambda that takes a username parameter and uses it to call
    // findByUsername() on the given UserRepository.
    // The loadByUsername() method has one simple rule: it must never return null.
    // Therefore, if the call to findByUsername() returns null, the lambda will throw a
    // UsernameNotFoundException (which is defined by Spring Security). Otherwise, the
    // User that was found will be returned.
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) {
                System.out.println("FOUND");
                return user;
            }
            System.out.println("NOT FOUND");
            throw new UsernameNotFoundException(
                    "User '" + username + "' not found");
        };
    }

    // But the index page, login page, and registration page should be available to unauthenticated users.
    // To configure security rules, we’ll need to declare a SecurityFilterChain bean.
    // The filterChain() method accepts an HttpSecurity object, which acts as a builder
    // that can be used to configure how security is handled at the web level. Once security
    // configuration is set up via the HttpSecurity object, a call to build() will create a
    // SecurityFilterChain that is returned from the bean method.
    // The following are among the many things you can configure with HttpSecurity:
    // - Requiring that certain security conditions be met before allowing a request to be served
    // - Configuring a custom login page
    // - Enabling users to log out of the application
    // - Configuring cross-site request forgery protection

    // Ensure that requests for /dashboard and /edituser are available only to authenticated users.
    // All other requests should be permitted for all users.
    // The call to authorizeRequests() returns an object on which you can specify URL paths
    // and patterns and the security requirements for those paths.
    // Requests for /dashboard and /edituser should be for users with a granted authority
    // of ROLE_USER. Don’t include the ROLE_ prefix on roles passed to hasRole(); it
    // will be assumed by hasRole().
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecConfig");
        return http
                .authorizeRequests()
                .antMatchers("/dashboard").access("hasRole('USER')")
                .antMatchers("/", "/**").access("permitAll()")
                .and() // replace built-in login page
                .formLogin()
                .loginPage("/login") // login page path
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutSuccessUrl("/home/allProducts")
                .and()
                .build();
    }
}
